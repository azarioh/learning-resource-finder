var swfmini=function(){function a(){if(!B){try{var a=v.getElementsByTagName("body")[0].appendChild(l("span"));a.parentNode.removeChild(a)}catch(b){return}B=!0;for(var c=y.length,d=0;c>d;d++)y[d]()}}function b(a){B?a():y[y.length]=a}function c(){}function d(){x&&e()}function e(){var a=v.getElementsByTagName("body")[0],b=l(p);b.setAttribute("type",t);var c=a.appendChild(b);if(c){var d=0;!function(){if(typeof c.GetVariable!=o){var e=c.GetVariable("$version");e&&(e=e.split(" ")[1].split(","),D.pv=[parseInt(e[0],10),parseInt(e[1],10),parseInt(e[2],10)])}else if(10>d)return d++,setTimeout(arguments.callee,10),void 0;a.removeChild(b),c=null}()}}function f(a){var b=null,c=k(a);if(c&&"OBJECT"==c.nodeName)if(typeof c.SetVariable!=o)b=c;else{var d=c.getElementsByTagName(p)[0];d&&(b=d)}return b}function g(a,b,c){var d,e=k(c);if(D.wk&&D.wk<312)return d;if(e)if(typeof a.id==o&&(a.id=c),D.ie&&D.win){var f="";for(var g in a)a[g]!=Object.prototype[g]&&("data"==g.toLowerCase()?b.movie=a[g]:"styleclass"==g.toLowerCase()?f+=' class="'+a[g]+'"':"classid"!=g.toLowerCase()&&(f+=" "+g+'="'+a[g]+'"'));var i="";for(var j in b)b[j]!=Object.prototype[j]&&(i+='<param name="'+j+'" value="'+b[j]+'" />');e.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+f+">"+i+"</object>",z[z.length]=a.id,d=k(a.id)}else{var m=l(p);m.setAttribute("type",t);for(var n in a)a[n]!=Object.prototype[n]&&("styleclass"==n.toLowerCase()?m.setAttribute("class",a[n]):"classid"!=n.toLowerCase()&&m.setAttribute(n,a[n]));for(var q in b)b[q]!=Object.prototype[q]&&"movie"!=q.toLowerCase()&&h(m,q,b[q]);e.parentNode.replaceChild(m,e),d=m}return d}function h(a,b,c){var d=l("param");d.setAttribute("name",b),d.setAttribute("value",c),a.appendChild(d)}function i(a){var b=k(a);b&&"OBJECT"==b.nodeName&&(D.ie&&D.win?(b.style.display="none",function(){4==b.readyState?j(a):setTimeout(arguments.callee,10)}()):b.parentNode.removeChild(b))}function j(a){var b=k(a);if(b){for(var c in b)"function"==typeof b[c]&&(b[c]=null);b.parentNode.removeChild(b)}}function k(a){var b=null;try{b=v.getElementById(a)}catch(c){}return b}function l(a){return v.createElement(a)}function m(a){var b=D.pv,c=a.split(".");return c[0]=parseInt(c[0],10),c[1]=parseInt(c[1],10)||0,c[2]=parseInt(c[2],10)||0,b[0]>c[0]||b[0]==c[0]&&b[1]>c[1]||b[0]==c[0]&&b[1]==c[1]&&b[2]>=c[2]?!0:!1}function n(a,b){if(C){var c,d=b?"visible":"hidden";B&&c&&k(a)&&(k(a).style.visibility=d)}}{var o="undefined",p="object",q=window.webshims,r="Shockwave Flash",s="ShockwaveFlash.ShockwaveFlash",t="application/x-shockwave-flash",u=window,v=document,w=navigator,x=!1,y=[d],z=[],A=[],B=!1,C=!0,D=function(){var a=typeof v.getElementById!=o&&typeof v.getElementsByTagName!=o&&typeof v.createElement!=o,b=w.userAgent.toLowerCase(),c=w.platform.toLowerCase(),d=c?/win/.test(c):/win/.test(b),e=c?/mac/.test(c):/mac/.test(b),f=/webkit/.test(b)?parseFloat(b.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):!1,g=!1,h=[0,0,0],i=null;if(typeof w.plugins!=o&&typeof w.plugins[r]==p)i=w.plugins[r].description,!i||typeof w.mimeTypes!=o&&w.mimeTypes[t]&&!w.mimeTypes[t].enabledPlugin||(x=!0,g=!1,i=i.replace(/^.*\s+(\S+\s+\S+$)/,"$1"),h[0]=parseInt(i.replace(/^(.*)\..*$/,"$1"),10),h[1]=parseInt(i.replace(/^.*\.(.*)\s.*$/,"$1"),10),h[2]=/[a-zA-Z]/.test(i)?parseInt(i.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0);else if(typeof u.ActiveXObject!=o)try{var j=new ActiveXObject(s);j&&(i=j.GetVariable("$version"),i&&(g=!0,i=i.split(" ")[1].split(","),h=[parseInt(i[0],10),parseInt(i[1],10),parseInt(i[2],10)]))}catch(k){}return{w3:a,pv:h,wk:f,ie:g,win:d,mac:e}}();!function(){D.ie&&D.win&&window.attachEvent&&window.attachEvent("onunload",function(){for(var a=A.length,b=0;a>b;b++)A[b][0].detachEvent(A[b][1],A[b][2]);for(var c=z.length,d=0;c>d;d++)i(z[d]);for(var e in D)D[e]=null;D=null;for(var f in swfmini)swfmini[f]=null;swfmini=null})}()}return q.ready("DOM",a),{registerObject:function(){},getObjectById:function(a){return D.w3?f(a):void 0},embedSWF:function(a,c,d,e,f,h,i,j,k,l){var q={success:!1,id:c};D.w3&&!(D.wk&&D.wk<312)&&a&&c&&d&&e&&f?(n(c,!1),b(function(){d+="",e+="";var b={};if(k&&typeof k===p)for(var h in k)b[h]=k[h];b.data=a,b.width=d,b.height=e;var r={};if(j&&typeof j===p)for(var s in j)r[s]=j[s];if(i&&typeof i===p)for(var t in i)typeof r.flashvars!=o?r.flashvars+="&"+t+"="+i[t]:r.flashvars=t+"="+i[t];if(m(f)){var u=g(b,r,c);b.id==c&&n(c,!0),q.success=!0,q.ref=u}else n(c,!0);l&&l(q)})):l&&l(q)},switchOffAutoHideShow:function(){C=!1},ua:D,getFlashPlayerVersion:function(){return{major:D.pv[0],minor:D.pv[1],release:D.pv[2]}},hasFlashPlayerVersion:m,createSWF:function(a,b,c){return D.w3?g(a,b,c):void 0},showExpressInstall:function(){},removeSWF:function(a){D.w3&&i(a)},createCSS:function(){},addDomLoadEvent:b,addLoadEvent:c,expressInstallCallback:function(){}}}();!function(a,b){"use strict";var c,d,e=b.$,f=a.audio&&a.video,g=!1,h=b.bugs,i="mediaelement-jaris",j=function(){b.ready(i,function(){b.mediaelement.createSWF||(b.mediaelement.loadSwf=!0,b.reTest([i],f))})},k=b.cfg,l=k.mediaelement;if(!l)return b.error("mediaelement wasn't implemented but loaded"),void 0;if(f){var m=document.createElement("video");if(a.videoBuffered="buffered"in m,a.mediaDefaultMuted="defaultMuted"in m,g="loop"in m,b.capturingEvents(["play","playing","waiting","paused","ended","durationchange","loadedmetadata","canplay","volumechange"]),a.videoBuffered||(b.addPolyfill("mediaelement-native-fix",{d:["dom-support"]}),b.loader.loadList(["mediaelement-native-fix"])),!l.preferFlash){var n={1:1},o=function(a){var c,f,g;!l.preferFlash&&(e(a.target).is("audio, video")||(g=a.target.parentNode)&&e("source",g).last()[0]==a.target)&&(c=e(a.target).closest("audio, video"))&&(f=c.prop("error"))&&!n[f.code]&&e(function(){d&&!l.preferFlash?(j(),b.ready("WINDOWLOAD "+i,function(){setTimeout(function(){l.preferFlash||!b.mediaelement.createSWF||c.is(".nonnative-api-active")||(l.preferFlash=!0,document.removeEventListener("error",o,!0),e("audio, video").each(function(){b.mediaelement.selectSource(this)}),b.error("switching mediaelements option to 'preferFlash', due to an error with native player: "+a.target.src+" Mediaerror: "+c.prop("error")+"first error: "+f))},9)})):document.removeEventListener("error",o,!0)})};document.addEventListener("error",o,!0),e("audio, video").each(function(){var a=e.prop(this,"error");return a&&!n[a]?(o({target:this}),!1):void 0})}}a.track&&!h.track&&!function(){if(h.track||(h.track="number"!=typeof e("<track />")[0].readyState),!h.track)try{new TextTrackCue(2,3,"")}catch(a){h.track=!0}}(),c=a.track&&!h.track,b.register("mediaelement-core",function(b,e,h,k,l,m){d=swfmini.hasFlashPlayerVersion("9.0.115"),b("html").addClass(d?"swf":"no-swf");var n=e.mediaelement;n.parseRtmp=function(a){var b,c,d,f=a.src.split("://"),g=f[1].split("/");for(a.server=f[0]+"://"+g[0]+"/",a.streamId=[],b=1,c=g.length;c>b;b++)d||-1===g[b].indexOf(":")||(g[b]=g[b].split(":")[1],d=!0),d?a.streamId.push(g[b]):a.server+=g[b]+"/";a.streamId.length||e.error("Could not parse rtmp url"),a.streamId=a.streamId.join("/")};var o=function(a,c){a=b(a);var d,e={src:a.attr("src")||"",elem:a,srcProp:a.prop("src")};return e.src?(d=a.attr("data-server"),null!=d&&(e.server=d),d=a.attr("type")||a.attr("data-type"),d?(e.type=d,e.container=b.trim(d.split(";")[0])):(c||(c=a[0].nodeName.toLowerCase(),"source"==c&&(c=(a.closest("video, audio")[0]||{nodeName:"video"}).nodeName.toLowerCase())),e.server?(e.type=c+"/rtmp",e.container=c+"/rtmp"):(d=n.getTypeForSrc(e.src,c,e),d&&(e.type=d,e.container=d))),e.container||b(a).attr("data-wsrecheckmimetype",""),d=a.attr("media"),d&&(e.media=d),("audio/rtmp"==e.type||"video/rtmp"==e.type)&&(e.server?e.streamId=e.src:n.parseRtmp(e)),e):e},p=!d&&"postMessage"in h&&f,q=function(){q.loaded||(q.loaded=!0,m.noAutoTrack||e.ready("WINDOWLOAD",function(){s(),e.loader.loadList(["track-ui"])}))},r=function(){var a;return function(){!a&&p&&(a=!0,e.loader.loadScript("https://www.youtube.com/player_api"),b(function(){e._polyfill(["mediaelement-yt"])}))}}(),s=function(){d?j():r()};e.addPolyfill("mediaelement-yt",{test:!p,d:["dom-support"]}),n.mimeTypes={audio:{"audio/ogg":["ogg","oga","ogm"],'audio/ogg;codecs="opus"':"opus","audio/mpeg":["mp2","mp3","mpga","mpega"],"audio/mp4":["mp4","mpg4","m4r","m4a","m4p","m4b","aac"],"audio/wav":["wav"],"audio/3gpp":["3gp","3gpp"],"audio/webm":["webm"],"audio/fla":["flv","f4a","fla"],"application/x-mpegURL":["m3u8","m3u"]},video:{"video/ogg":["ogg","ogv","ogm"],"video/mpeg":["mpg","mpeg","mpe"],"video/mp4":["mp4","mpg4","m4v"],"video/quicktime":["mov","qt"],"video/x-msvideo":["avi"],"video/x-ms-asf":["asf","asx"],"video/flv":["flv","f4v"],"video/3gpp":["3gp","3gpp"],"video/webm":["webm"],"application/x-mpegURL":["m3u8","m3u"],"video/MP2T":["ts"]}},n.mimeTypes.source=b.extend({},n.mimeTypes.audio,n.mimeTypes.video),n.getTypeForSrc=function(a,c){if(-1!=a.indexOf("youtube.com/watch?")||-1!=a.indexOf("youtube.com/v/"))return"video/youtube";if(0===a.indexOf("rtmp"))return c+"/rtmp";a=a.split("?")[0].split("#")[0].split("."),a=a[a.length-1];var d;return b.each(n.mimeTypes[c],function(b,c){return-1!==c.indexOf(a)?(d=b,!1):void 0}),d},n.srces=function(a,c){if(a=b(a),!c){c=[];var d=a[0].nodeName.toLowerCase(),e=o(a,d);return e.src?c.push(e):b("source",a).each(function(){e=o(this,d),e.src&&c.push(e)}),c}a.removeAttr("src").removeAttr("type").find("source").remove(),b.isArray(c)||(c=[c]),c.forEach(function(c){"string"==typeof c&&(c={src:c}),a.append(b(k.createElement("source")).attr(c))})},b.fn.loadMediaSrc=function(a,c){return this.each(function(){c!==l&&(b(this).removeAttr("poster"),c&&b.attr(this,"poster",c)),n.srces(this,a),b(this).mediaLoad()})},n.swfMimeTypes=["video/3gpp","video/x-msvideo","video/quicktime","video/x-m4v","video/mp4","video/m4p","video/x-flv","video/flv","audio/mpeg","audio/aac","audio/mp4","audio/x-m4a","audio/m4a","audio/mp3","audio/x-fla","audio/fla","youtube/flv","video/jarisplayer","jarisplayer/jarisplayer","video/youtube","video/rtmp","audio/rtmp"],n.canThirdPlaySrces=function(a,c){var e="";return(d||p)&&(a=b(a),c=c||n.srces(a),b.each(c,function(a,b){return b.container&&b.src&&(d&&-1!=n.swfMimeTypes.indexOf(b.container)||p&&"video/youtube"==b.container)?(e=b,!1):void 0})),e};var t={};n.canNativePlaySrces=function(a,c){var d="";if(f){a=b(a);var e=(a[0].nodeName||"").toLowerCase(),g=(t[e]||{prop:{_supvalue:!1}}).prop._supvalue||a[0].canPlayType;if(!g)return d;c=c||n.srces(a),b.each(c,function(b,c){return c.type&&g.call(a[0],c.type)?(d=c,!1):void 0})}return d};var u=/^\s*application\/octet\-stream\s*$/i,v=function(){var a=u.test(b.attr(this,"type")||"");return a&&b(this).removeAttr("type"),a};n.setError=function(a,c){if(b("source",a).filter(v).length){e.error('"application/octet-stream" is a useless mimetype for audio/video. Please change this attribute.');try{b(a).mediaLoad()}catch(d){}}else c||(c="can't play sources"),b(a).pause().data("mediaerror",c),e.error("mediaelementError: "+c),setTimeout(function(){b(a).data("mediaerror")&&b(a).addClass("media-error").trigger("mediaerror")},1)};var w=function(){var a,c=d?i:"mediaelement-yt";return function(d,f,g){e.ready(c,function(){n.createSWF&&b(d).parent()[0]?n.createSWF(d,f,g):a||(a=!0,s(),w(d,f,g))}),a||!p||n.createSWF||r()}}(),x=function(a,b,c,d,e){var f;c||c!==!1&&b&&"third"==b.isActive?(f=n.canThirdPlaySrces(a,d),f?w(a,f,b):e?n.setError(a,!1):x(a,b,!1,d,!0)):(f=n.canNativePlaySrces(a,d),f?b&&"third"==b.isActive&&n.setActive(a,"html5",b):e?(n.setError(a,!1),b&&"third"==b.isActive&&n.setActive(a,"html5",b)):x(a,b,!0,d,!0))},y=/^(?:embed|object|datalist)$/i,z=function(a,c){var d=e.data(a,"mediaelementBase")||e.data(a,"mediaelementBase",{}),f=n.srces(a),g=a.parentNode;clearTimeout(d.loadTimer),b(a).removeClass("media-error"),b.data(a,"mediaerror",!1),f.length&&g&&1==g.nodeType&&!y.test(g.nodeName||"")&&(c=c||e.data(a,"mediaelement"),n.sortMedia&&f.sort(n.sortMedia),x(a,c,m.preferFlash||l,f))};n.selectSource=z,b(k).on("ended",function(a){var c=e.data(a.target,"mediaelement");(!g||c&&"html5"!=c.isActive||b.prop(a.target,"loop"))&&setTimeout(function(){!b.prop(a.target,"paused")&&b.prop(a.target,"loop")&&b(a.target).prop("currentTime",0).play()},1)});var A=!1,B=function(){var c=function(){if(e.implement(this,"mediaelement")&&(z(this),a.mediaDefaultMuted||null==b.attr(this,"muted")||b.prop(this,"muted",!0),f&&(!g||"ActiveXObject"in h))){var c,d,i=this,j=function(){var a=b.prop(i,"buffered");if(a){for(var c="",d=0,e=a.length;e>d;d++)c+=a.end(d);return c}},k=function(){var a=j();a!=d&&(d=a,e.info("needed to trigger progress manually"),b(i).triggerHandler("progress"))};b(this).on({"play loadstart progress":function(a){"progress"==a.type&&(d=j()),clearTimeout(c),c=setTimeout(k,400)},"emptied stalled mediaerror abort suspend":function(a){"emptied"==a.type&&(d=!1),clearTimeout(c)}}),"ActiveXObject"in h&&b.prop(this,"paused")&&!b.prop(this,"readyState")&&b(this).is('audio[preload="none"][controls]:not([autoplay],.nonnative-api-active)')&&b(this).prop("preload","metadata").mediaLoad()}};e.ready("dom-support",function(){A=!0,g||e.defineNodeNamesBooleanProperty(["audio","video"],"loop"),["audio","video"].forEach(function(a){var c;c=e.defineNodeNameProperty(a,"load",{prop:{value:function(){var a=e.data(this,"mediaelement");z(this,a),!f||a&&"html5"!=a.isActive||!c.prop._supvalue||c.prop._supvalue.apply(this,arguments),b(this).triggerHandler("wsmediareload")}}}),t[a]=e.defineNodeNameProperty(a,"canPlayType",{prop:{value:function(c){var e="";return f&&t[a].prop._supvalue&&(e=t[a].prop._supvalue.call(this,c),"no"==e&&(e="")),!e&&d&&(c=b.trim((c||"").split(";")[0]),-1!=n.swfMimeTypes.indexOf(c)&&(e="maybe")),e}}})}),e.onNodeNamesPropertyModify(["audio","video"],["src","poster"],{set:function(){var a=this,b=e.data(a,"mediaelementBase")||e.data(a,"mediaelementBase",{});clearTimeout(b.loadTimer),b.loadTimer=setTimeout(function(){z(a),a=null},9)}}),e.addReady(function(a,d){var e=b("video, audio",a).add(d.filter("video, audio")).each(c);!q.loaded&&b("track",e).length&&q(),e=null})}),f&&!A&&e.addReady(function(a,c){A||b("video, audio",a).add(c.filter("video, audio")).each(function(){return n.canNativePlaySrces(this)?void 0:(s(),A=!0,!1)})})};c&&e.defineProperty(TextTrack.prototype,"shimActiveCues",{get:function(){return this._shimActiveCues||this.activeCues}}),f?(e.isReady("mediaelement-core",!0),B(),e.ready("WINDOWLOAD mediaelement",s)):e.ready(i,B),e.ready("track",q)})}(Modernizr,webshims),webshims.register("track",function(a,b,c,d){"use strict";var e=b.mediaelement,f=((new Date).getTime(),a.fn.addBack?"addBack":"andSelf",{subtitles:1,captions:1,descriptions:1}),g=a("<track />"),h=Modernizr.ES5&&Modernizr.objectAccessor,i=function(a){var c={};return a.addEventListener=function(a,d){c[a]&&b.error("always use $.on to the shimed event: "+a+" already bound fn was: "+c[a]+" your fn was: "+d),c[a]=d},a.removeEventListener=function(a,d){c[a]&&c[a]!=d&&b.error("always use $.on/$.off to the shimed event: "+a+" already bound fn was: "+c[a]+" your fn was: "+d),c[a]&&delete c[a]},a},j={getCueById:function(a){for(var b=null,c=0,d=this.length;d>c;c++)if(this[c].id===a){b=this[c];break}return b}},k={0:"disabled",1:"hidden",2:"showing"},l={shimActiveCues:null,_shimActiveCues:null,activeCues:null,cues:null,kind:"subtitles",label:"",language:"",id:"",mode:"disabled",oncuechange:null,toString:function(){return"[object TextTrack]"},addCue:function(a){if(this.cues){var c=this.cues[this.cues.length-1];c&&c.startTime>a.startTime&&b.error("cue startTime higher than previous cue's startTime")}else this.cues=e.createCueList();a.track&&a.track.removeCue&&a.track.removeCue(a),a.track=this,this.cues.push(a)},removeCue:function(a){var c=this.cues||[],d=0,e=c.length;if(a.track!=this)return b.error("cue not part of track"),void 0;for(;e>d;d++)if(c[d]===a){c.splice(d,1),a.track=null;break}return a.track?(b.error("cue not part of track"),void 0):void 0}},m=["kind","label","srclang"],n={srclang:"language"},o=Function.prototype.call.bind(Object.prototype.hasOwnProperty),p=function(c,d){var e,f,g=[],h=[],i=[];if(c||(c=b.data(this,"mediaelementBase")||b.data(this,"mediaelementBase",{})),d||(c.blockTrackListUpdate=!0,d=a.prop(this,"textTracks"),c.blockTrackListUpdate=!1),clearTimeout(c.updateTrackListTimer),a("track",this).each(function(){var b=a.prop(this,"track");i.push(b),-1==d.indexOf(b)&&h.push(b)}),c.scriptedTextTracks)for(e=0,f=c.scriptedTextTracks.length;f>e;e++)i.push(c.scriptedTextTracks[e]),-1==d.indexOf(c.scriptedTextTracks[e])&&h.push(c.scriptedTextTracks[e]);for(e=0,f=d.length;f>e;e++)-1==i.indexOf(d[e])&&g.push(d[e]);if(g.length||h.length){for(d.splice(0),e=0,f=i.length;f>e;e++)d.push(i[e]);for(e=0,f=g.length;f>e;e++)a([d]).triggerHandler(a.Event({type:"removetrack",track:g[e]}));for(e=0,f=h.length;f>e;e++)a([d]).triggerHandler(a.Event({type:"addtrack",track:h[e]}));(c.scriptedTextTracks||g.length)&&a(this).triggerHandler("updatetrackdisplay")}},q=function(c,d){d||(d=b.data(c,"trackData")),d&&!d.isTriggering&&(d.isTriggering=!0,setTimeout(function(){a(c).closest("audio, video").triggerHandler("updatetrackdisplay"),d.isTriggering=!1},1))},r=function(){var c={subtitles:{subtitles:1,captions:1},descriptions:{descriptions:1},chapters:{chapters:1}};return c.captions=c.subtitles,function(d){var e,f,g=a.prop(d,"default");return g&&"metadata"!=(e=a.prop(d,"kind"))&&(f=a(d).parent().find("track[default]").filter(function(){return!!c[e][a.prop(this,"kind")]})[0],f!=d&&(g=!1,b.error("more than one default track of a specific kind detected. Fall back to default = false"))),g}}(),s=a("<div />")[0],t=function(a,c,d){3!=arguments.length&&b.error("wrong arguments.length for TextTrackCue.constructor"),this.startTime=a,this.endTime=c,this.text=d,i(this)};t.prototype={onenter:null,onexit:null,pauseOnExit:!1,getCueAsHTML:function(){var a,b="",c="",f=d.createDocumentFragment();return o(this,"getCueAsHTML")||(a=this.getCueAsHTML=function(){var a,d;if(b!=this.text)for(b=this.text,c=e.parseCueTextToHTML(b),s.innerHTML=c,a=0,d=s.childNodes.length;d>a;a++)f.appendChild(s.childNodes[a].cloneNode(!0));return f.cloneNode(!0)}),a?a.apply(this,arguments):f.cloneNode(!0)},track:null,id:""},c.TextTrackCue=t,e.createCueList=function(){return a.extend([],j)},e.parseCueTextToHTML=function(){var a=/(<\/?[^>]+>)/gi,b=/^(?:c|v|ruby|rt|b|i|u)/,c=/\<\s*\//,d=function(a,b,d,e){var f;return c.test(e)?f="</"+a+">":(d.splice(0,1),f="<"+a+" "+b+'="'+d.join(" ").replace(/\"/g,"&#34;")+'">'),f},e=function(a){var c=a.replace(/[<\/>]+/gi,"").split(/[\s\.]+/);return c[0]&&(c[0]=c[0].toLowerCase(),b.test(c[0])?"c"==c[0]?a=d("span","class",c,a):"v"==c[0]&&(a=d("q","title",c,a)):a=""),a};return function(b){return b.replace(a,e)}}(),e.loadTextTrack=function(c,d,g,h){var i="play playing updatetrackdisplay",j=g.track,k=function(){var f,h,l,m;if("disabled"!=j.mode&&a.attr(d,"src")&&(l=a.prop(d,"src"))&&(a(c).off(i,k),!g.readyState)){f=function(){g.readyState=3,j.cues=null,j.activeCues=j.shimActiveCues=j._shimActiveCues=null,a(d).triggerHandler("error")},g.readyState=1;try{j.cues=e.createCueList(),j.activeCues=j.shimActiveCues=j._shimActiveCues=e.createCueList(),m=function(){h=a.ajax({dataType:"text",url:l,success:function(i){"text/vtt"!=h.getResponseHeader("content-type")&&b.error("set the mime-type of your WebVTT files to text/vtt. see: http://dev.w3.org/html5/webvtt/#text/vtt"),e.parseCaptions(i,j,function(b){b&&"length"in b?(g.readyState=2,a(d).triggerHandler("load"),a(c).triggerHandler("updatetrackdisplay")):f()})},error:f})},a.ajax?m():(b.ready("$ajax",m),b.loader.loadList(["$ajax"]))}catch(n){f(),b.error(n)}}};g.readyState=0,j.shimActiveCues=null,j._shimActiveCues=null,j.activeCues=null,j.cues=null,a(c).off(i,k),a(c).on(i,k),h&&(j.mode=f[j.kind]?"showing":"hidden",k())},e.createTextTrack=function(c,d){var f,g;return d.nodeName&&(g=b.data(d,"trackData"),g&&(q(d,g),f=g.track)),f||(f=i(b.objectCreate(l)),h||m.forEach(function(b){var c=a.prop(d,b);c&&(f[n[b]||b]=c)}),d.nodeName?(h&&m.forEach(function(c){b.defineProperty(f,n[c]||c,{get:function(){return a.prop(d,c)}})}),f.id=a(d).prop("id"),g=b.data(d,"trackData",{track:f}),e.loadTextTrack(c,d,g,r(d))):(h&&m.forEach(function(a){b.defineProperty(f,n[a]||a,{value:d[a],writeable:!1})}),f.cues=e.createCueList(),f.activeCues=f._shimActiveCues=f.shimActiveCues=e.createCueList(),f.mode="hidden",f.readyState=2),"subtitles"!=f.kind||f.language||b.error("you must provide a language for track in subtitles state"),f.__wsmode=f.mode),f},e.parseCaptionChunk=function(){var a=/^(\d{2})?:?(\d{2}):(\d{2})\.(\d+)\s+\-\-\>\s+(\d{2})?:?(\d{2}):(\d{2})\.(\d+)\s*(.*)/,c=/^(DEFAULTS|DEFAULT)\s+\-\-\>\s+(.*)/g,d=/^(STYLE|STYLES)\s+\-\-\>\s*\n([\s\S]*)/g,e=/^(COMMENT|COMMENTS)\s+\-\-\>\s+(.*)/g;return function(f){var g,h,i,j,k,l,m,n,o,p;if(n=c.exec(f))return null;if(n=d.exec(f))return null;if(n=e.exec(f))return null;for(g=f.split(/\n/g);!g[0].replace(/\s+/gi,"").length&&g.length>0;)g.shift();for(g[0].match(/^\s*[a-z0-9-\_]+\s*$/gi)&&(m=String(g.shift().replace(/\s*/gi,""))),l=0;l<g.length;l++){var q=g[l];(o=a.exec(q))&&(k=o.slice(1),h=parseInt(60*(k[0]||0)*60,10)+parseInt(60*(k[1]||0),10)+parseInt(k[2]||0,10)+parseFloat("0."+(k[3]||0)),i=parseInt(60*(k[4]||0)*60,10)+parseInt(60*(k[5]||0),10)+parseInt(k[6]||0,10)+parseFloat("0."+(k[7]||0))),g=g.slice(0,l).concat(g.slice(l+1));break}return h||i?(j=g.join("\n"),p=new t(h,i,j),m&&(p.id=m),p):(b.warn("couldn't extract time information: "+[h,i,g.join("\n"),m].join(" ; ")),null)}}(),e.parseCaptions=function(a,c,d){{var f,g,h,i,j;e.createCueList()}a?(h=/^WEBVTT(\s*FILE)?/gi,g=function(k,l){for(;l>k;k++){if(f=a[k],h.test(f))j=!0;else if(f.replace(/\s*/gi,"").length){if(!j){b.error("please use WebVTT format. This is the standard"),d(null);break}f=e.parseCaptionChunk(f,k),f&&c.addCue(f)}if(i<(new Date).getTime()-30){k++,setTimeout(function(){i=(new Date).getTime(),g(k,l)},90);break}}k>=l&&(j||b.error("please use WebVTT format. This is the standard"),d(c.cues))},a=a.replace(/\r\n/g,"\n"),setTimeout(function(){a=a.replace(/\r/g,"\n"),setTimeout(function(){i=(new Date).getTime(),a=a.split(/\n\n+/g),g(0,a.length)},9)},9)):b.error("Required parameter captionData not supplied.")},e.createTrackList=function(c,d){return d=d||b.data(c,"mediaelementBase")||b.data(c,"mediaelementBase",{}),d.textTracks||(d.textTracks=[],b.defineProperties(d.textTracks,{onaddtrack:{value:null},onremovetrack:{value:null},onchange:{value:null},getTrackById:{value:function(a){for(var b=null,c=0;c<d.textTracks.length;c++)if(a==d.textTracks[c].id){b=d.textTracks[c];break}return b}}}),i(d.textTracks),a(c).on("updatetrackdisplay",function(){for(var b,c=0;c<d.textTracks.length;c++)b=d.textTracks[c],b.__wsmode!=b.mode&&(b.__wsmode=b.mode,a([d.textTracks]).triggerHandler("change"))})),d.textTracks},Modernizr.track||(b.defineNodeNamesBooleanProperty(["track"],"default"),b.reflectProperties(["track"],["srclang","label"]),b.defineNodeNameProperties("track",{src:{reflect:!0,propType:"src"}})),b.defineNodeNameProperties("track",{kind:{attr:Modernizr.track?{set:function(a){var c=b.data(this,"trackData");this.setAttribute("data-kind",a),c&&(c.attrKind=a)},get:function(){var a=b.data(this,"trackData");return a&&"attrKind"in a?a.attrKind:this.getAttribute("kind")}}:{},reflect:!0,propType:"enumarated",defaultValue:"subtitles",limitedTo:["subtitles","captions","descriptions","chapters","metadata"]}}),a.each(m,function(c,d){var e=n[d]||d;b.onNodeNamesPropertyModify("track",d,function(){var c=b.data(this,"trackData");c&&("kind"==d&&q(this,c),h||(c.track[e]=a.prop(this,d)))})}),b.onNodeNamesPropertyModify("track","src",function(c){if(c){var d,f=b.data(this,"trackData");f&&(d=a(this).closest("video, audio"),d[0]&&e.loadTextTrack(d,this,f))}}),b.defineNodeNamesProperties(["track"],{ERROR:{value:3},LOADED:{value:2},LOADING:{value:1},NONE:{value:0},readyState:{get:function(){return(b.data(this,"trackData")||{readyState:0}).readyState},writeable:!1},track:{get:function(){return e.createTextTrack(a(this).closest("audio, video")[0],this)},writeable:!1}},"prop"),b.defineNodeNamesProperties(["audio","video"],{textTracks:{get:function(){var a=this,c=b.data(a,"mediaelementBase")||b.data(a,"mediaelementBase",{}),d=e.createTrackList(a,c);return c.blockTrackListUpdate||p.call(a,c,d),d},writeable:!1},addTextTrack:{value:function(a,c,d){var f=e.createTextTrack(this,{kind:g.prop("kind",a||"").prop("kind"),label:c||"",srclang:d||""}),h=b.data(this,"mediaelementBase")||b.data(this,"mediaelementBase",{});return h.scriptedTextTracks||(h.scriptedTextTracks=[]),h.scriptedTextTracks.push(f),p.call(this),f}}},"prop");var u=function(c){if(a(c.target).is("audio, video")){var d=b.data(c.target,"mediaelementBase");d&&(clearTimeout(d.updateTrackListTimer),d.updateTrackListTimer=setTimeout(function(){p.call(c.target,d)},0))}},v=function(a,b){return b.readyState||a.readyState},w=function(a){a.originalEvent&&a.stopImmediatePropagation()},x=function(){if(b.implement(this,"track")){var c,d,e=a.prop(this,"track"),f=this.track;f&&(c=a.prop(this,"kind"),d=v(this,f),(f.mode||d)&&(e.mode=k[f.mode]||f.mode),"descriptions"!=c&&(f.mode="string"==typeof f.mode?"disabled":0,this.kind="metadata",a(this).attr({kind:c}))),a(this).on("load error",w)}};b.addReady(function(c,d){var e=d.filter("video, audio, track").closest("audio, video");a("video, audio",c).add(e).each(function(){p.call(this)}).on("emptied updatetracklist wsmediareload",u).each(function(){if(Modernizr.track){var c=a.prop(this,"textTracks"),d=this.textTracks;c.length!=d.length&&b.error("textTracks couldn't be copied"),a("track",this).each(x)}}),e.each(function(){var a=this,c=b.data(a,"mediaelementBase");c&&(clearTimeout(c.updateTrackListTimer),c.updateTrackListTimer=setTimeout(function(){p.call(a,c)},9))})}),Modernizr.texttrackapi&&a("video, audio").trigger("trackapichange")});