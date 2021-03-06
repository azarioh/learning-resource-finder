package learningresourcefinder.service;

import java.util.ArrayList;
import java.util.List;

import learningresourcefinder.model.Competence;
import learningresourcefinder.model.Cycle;
import learningresourcefinder.repository.CompetenceRepository;
import learningresourcefinder.util.CompetenceNode;
import learningresourcefinder.web.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenceNodeService {
    
	final int NB_OF_COLUMNS = 3;

	@Autowired CompetenceRepository competenceRepository;
	
	/** Returns the root of a CompetenceNode tree, which is a copy of the Competence tree, filtered to include only the elements of the list */
	public CompetenceNode buildCompetenceNodeTree(Cycle cycle){
		
		List<Competence> competencesFromCycle = Cache.getInstance().getComputedCategoriesByCycles(cycle.getId());   // We only want to include these competences.
		Competence root = competenceRepository.findRoot();

		CompetenceNode rootNode = new CompetenceNode(root, null);
		for (Competence child : root.getChildren()) {
			processNode(child, rootNode, 1, competencesFromCycle);
		}

		return rootNode;
	}

	// Recursive method to run through the Competence tree 
	private void processNode(Competence currentCompetence, CompetenceNode parentCompetenceNode, int level, List<Competence> competencesFromCycle) {
	    // Temporary fix to not show the ugly full "socle de catégories" tree: we only show 2 levels.
	    if (level >= 6) {
	        return;
	    }
	    
	    
		if(competencesFromCycle.contains(currentCompetence)) {  // Ok, it's in the list of competences we need to get.
			// We create the corresponding CompetenceNode
			CompetenceNode competenceNode = new CompetenceNode(currentCompetence, parentCompetenceNode);
			// We recursively process the childs.
			for(Competence childCompetence : currentCompetence.getChildren()){
				processNode(childCompetence, competenceNode, level+1, competencesFromCycle);
			}
		}
	}		

	/** Returns each element of the list is a column (is the list of nodes to display in the column) */
	public List<List<CompetenceNode>> splitCompetenceNodesInColumns(CompetenceNode root) {
		////// 1. We count how many lines we need display in total.
		int totalAmountOfLinesToDisplay = 0;
		
		// Get the number of main topics (Math, French ...)
	    totalAmountOfLinesToDisplay = root.getChildren().size();
	    
	    // Add number of lines for the 2 next levels of each topic
	    for(CompetenceNode competenceNodeLevel1 : root.getChildren()){
	        totalAmountOfLinesToDisplay += competenceNodeLevel1.getChildren().size();
	        for(CompetenceNode competenceNodeLevel2 : root.getChildren()){
	            totalAmountOfLinesToDisplay += competenceNodeLevel2.getChildren().size();
	        }
	    }
		
		////// 2. We split the nodes into columns of (hopefully) an equivalent amount of lined in each. 
		int amountOfLinesToDisplayAlreadyCurrentColumn = 0;
		List<List<CompetenceNode>> columnsList = new ArrayList<>();		  
		List<CompetenceNode> currentColumn = new ArrayList<>();
		columnsList.add(currentColumn);

		for(CompetenceNode competenceNodeSecondLevel : root.getChildren()){  // These are "Math", "French", etc.
				currentColumn.add(competenceNodeSecondLevel );
				
				// Count total number of lines for current Topic (3 levels)
				amountOfLinesToDisplayAlreadyCurrentColumn += competenceNodeSecondLevel.getChildren().size();
				for(CompetenceNode competenceNodeThirdLevel : root.getChildren()){
				    amountOfLinesToDisplayAlreadyCurrentColumn += competenceNodeThirdLevel.getChildren().size();
				}
					
				// Do we need to jump to the next column?
					
				// If just 3 topics, ensure they are displayed automatically into 3 columns
				if ((amountOfLinesToDisplayAlreadyCurrentColumn) > totalAmountOfLinesToDisplay / NB_OF_COLUMNS   
						&& columnsList.size() < NB_OF_COLUMNS  || (root.getChildren().size() == 3)) { 
		
					currentColumn = new ArrayList<>();
					columnsList.add(currentColumn);
					amountOfLinesToDisplayAlreadyCurrentColumn = 0;
				}
		}
		
		// Remove the last column if empty
		if (currentColumn.size() == 0) {
			columnsList.remove(currentColumn);
		}

		return columnsList;
	}

}
