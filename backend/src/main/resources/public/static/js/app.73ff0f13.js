(function(e){function t(t){for(var r,a,i=t[0],c=t[1],u=t[2],d=0,f=[];d<i.length;d++)a=i[d],o[a]&&f.push(o[a][0]),o[a]=0;for(r in c)Object.prototype.hasOwnProperty.call(c,r)&&(e[r]=c[r]);l&&l(t);while(f.length)f.shift()();return s.push.apply(s,u||[]),n()}function n(){for(var e,t=0;t<s.length;t++){for(var n=s[t],r=!0,i=1;i<n.length;i++){var c=n[i];0!==o[c]&&(r=!1)}r&&(s.splice(t--,1),e=a(a.s=n[0]))}return e}var r={},o={app:0},s=[];function a(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,a),n.l=!0,n.exports}a.m=e,a.c=r,a.d=function(e,t,n){a.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},a.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,t){if(1&t&&(e=a(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(a.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)a.d(n,r,function(t){return e[t]}.bind(null,r));return n},a.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return a.d(t,"a",t),t},a.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},a.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],c=i.push.bind(i);i.push=t,i=i.slice();for(var u=0;u<i.length;u++)t(i[u]);var l=c;s.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"12b9":function(e,t,n){},"3cf4":function(e,t,n){"use strict";var r=n("a57d"),o=n.n(r);o.a},4267:function(e,t,n){"use strict";var r=n("12b9"),o=n.n(r);o.a},"56d7":function(e,t,n){"use strict";n.r(t);n("14c6"),n("08c1"),n("4842"),n("d9fc");var r=n("2b0e"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("div",{attrs:{id:"nav"}},[n("router-link",{attrs:{to:"/"}},[e._v("Home")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/login"}},[e._v("Inloggen")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/controlpanel"}},[e._v("Control Panel")])],1),n("router-view",{key:e.$route.fullPath})],1)},s=[],a={name:"app",data:function(){return{msg:"Welkom!"}}},i=a,c=(n("5c0b"),n("2877")),u=Object(c["a"])(i,o,s,!1,null,null,null),l=u.exports,d=n("8c4f"),f=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"home"},[r("img",{attrs:{src:n("ad5f")}}),r("h1",[e._v("Welkom!")]),r("div",{staticClass:"buttons"},[r("button",{on:{click:function(t){return e.setSort("Studenten")}}},[e._v("Studenten")]),r("button",{on:{click:function(t){return e.setSort("Docenten")}}},[e._v("Docenten")]),r("button",{on:{click:function(t){return e.setSort("OP")}}},[e._v("Onderwijsondersteunend Personeel")]),r("button",{attrs:{id:"actionButton"},on:{click:function(t){return e.check()}}},[e._v("Check")])]),r("div",{attrs:{id:"dienst_table"}},e._l(e.diensten,function(t){return r("div",{key:t.id},[r("div",{staticClass:"card"},[r("div",{staticClass:"card-body"},[r("h6",{staticClass:"card-title"},[e._v("\n                      "+e._s(t.name)+"\n                  ")]),r("p",{staticClass:"card-text"}),r("div",[e._v("\n                          "+e._s(t.status)+"\n                      ")])])])])}),0)])},p=[],v=(n("6762"),n("2fdb"),n("55dd"),n("7f7f"),n("bc3a")),h=n.n(v),m=h.a.create({baseURL:"/api"}),g={status:function(){return m.get("/status")},createDienst:function(e){return m.post("/dienst/create/"+e.name+"/"+e.key)},createDienstWithRelevance:function(e){return m.post("/dienst/create/"+e.name+"/"+e.key+"/"+e.relevance)},getSecured:function(e,t){return m.get("/secured/",{auth:{username:e,password:t}})}},b={name:"home",mounted:function(){this.check()},data:function(){return{diensten:[],sortBy:"None"}},methods:{check:function(){var e=this;this.diensten=[],g.status().then(function(e){return e.data.Diensten}).then(function(t){for(var n=0;n<t.length;n++){var r=t[n];e.diensten.push({id:r.dienstId,key:r.key,name:r.name,relevance:r.relevance,status:r.status,score:0})}}).then(function(t){e.sort(e.diensten)}).catch(function(e){console.log(e)})},sort:function(e){var t=function(t){for(var n=0;n<e.length;n++){var r=e[n];r.score=0,null==r.relevance&&(r.score+=1),"None"!=t&&null!=r.relevance&&(r.score+=2*r.relevance.includes(t))}};t(this.sortBy),this.diensten.sort(function(e,t){return-1*(e.score-t.score)})},setSort:function(e){this.sortBy=e,this.check()}}},_=b,k=(n("4267"),Object(c["a"])(_,f,p,!1,null,"38feea72",null)),y=k.exports,P=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"dienst_workshop"},[n("h1",[e._v(" Dienst "+e._s(e.$route.params.action)+" ")]),n("br"),n("input",{directives:[{name:"model",rawName:"v-model",value:e.dienst.name,expression:"dienst.name"}],attrs:{type:"text",placeholder:"Naam"},domProps:{value:e.dienst.name},on:{input:function(t){t.target.composing||e.$set(e.dienst,"name",t.target.value)}}}),n("input",{directives:[{name:"model",rawName:"v-model",value:e.dienst.key,expression:"dienst.key"}],attrs:{type:"text",placeholder:"URL"},domProps:{value:e.dienst.key},on:{input:function(t){t.target.composing||e.$set(e.dienst,"key",t.target.value)}}}),n("br"),n("br"),n("h4",[e._v("Relevant voor:")]),n("input",{directives:[{name:"model",rawName:"v-model",value:e.Studenten,expression:"Studenten"}],attrs:{type:"checkbox",id:"studentenCheckbox"},domProps:{checked:Array.isArray(e.Studenten)?e._i(e.Studenten,null)>-1:e.Studenten},on:{change:function(t){var n=e.Studenten,r=t.target,o=!!r.checked;if(Array.isArray(n)){var s=null,a=e._i(n,s);r.checked?a<0&&(e.Studenten=n.concat([s])):a>-1&&(e.Studenten=n.slice(0,a).concat(n.slice(a+1)))}else e.Studenten=o}}}),n("label",{attrs:{for:"studentenCheckbox"}},[e._v(" Studenten")]),n("br"),n("input",{directives:[{name:"model",rawName:"v-model",value:e.Docenten,expression:"Docenten"}],attrs:{type:"checkbox",id:"docentenCheckbox"},domProps:{checked:Array.isArray(e.Docenten)?e._i(e.Docenten,null)>-1:e.Docenten},on:{change:function(t){var n=e.Docenten,r=t.target,o=!!r.checked;if(Array.isArray(n)){var s=null,a=e._i(n,s);r.checked?a<0&&(e.Docenten=n.concat([s])):a>-1&&(e.Docenten=n.slice(0,a).concat(n.slice(a+1)))}else e.Docenten=o}}}),n("label",{attrs:{for:"docentenCheckbox"}},[e._v(" Docenten")]),n("br"),n("input",{directives:[{name:"model",rawName:"v-model",value:e.OP,expression:"OP"}],attrs:{type:"checkbox",id:"opCheckbox"},domProps:{checked:Array.isArray(e.OP)?e._i(e.OP,null)>-1:e.OP},on:{change:function(t){var n=e.OP,r=t.target,o=!!r.checked;if(Array.isArray(n)){var s=null,a=e._i(n,s);r.checked?a<0&&(e.OP=n.concat([s])):a>-1&&(e.OP=n.slice(0,a).concat(n.slice(a+1)))}else e.OP=o}}}),n("label",{attrs:{for:"opCheckbox"}},[e._v(" Onderwijsondersteunend Personeel")]),n("br"),n("br"),e.actions.create?n("button",{on:{click:function(t){return e.createDienst()}}},[e._v("Aanmaken")]):e._e(),e.actions.change?n("button",{on:{click:function(t){return e.deleteDienst()}}},[e._v("Wijzigen")]):e._e(),e.actions.delete?n("button",{on:{click:function(t){return e.changeDienst()}}},[e._v("Verwijderen")]):e._e()])},w=[],x={name:"dienst_workshop",mounted:function(){"Wijzigen"==this.$route.params.action?(this.actions.create=!1,this.actions.change=!0,this.actions.delete=!1):"Verwijderen"==this.$route.params.action?(this.actions.create=!1,this.actions.change=!1,this.actions.delete=!0):(this.actions.create=!0,this.actions.change=!1,this.actions.delete=!1)},data:function(){return{dienst:{id:0,name:"",key:"",relevance:""},actions:{create:!0,change:!1,delete:!1},Studenten:!1,Docenten:!1,OP:!1}},methods:{createDienst:function(){var e=this;this.dienst.relevance="";var t=this,n=function(e,t){return""!=e&&(e+=","),e+=t,e};""!=t.dienst.name&&""!=t.dienst.key?(t.Studenten&&(t.dienst.relevance=n(t.dienst.relevance,"Studenten")),t.Docenten&&(t.dienst.relevance=n(t.dienst.relevance,"Docenten")),t.OP&&(t.dienst.relevance=n(t.dienst.relevance,"OP")),console.log(t.dienst.relevance),""==t.dienst.relevance?g.createDienst(t.dienst).then(function(e){console.log(e)}).then(function(){e.$router.push("/controlpanel")}):g.createDienstWithRelevance(t.dienst).then(function(e){console.log(e)}).then(function(){e.$router.push("/controlpanel")})):alert("Vul een naam en een URL in.")}}},S=x,O=Object(c["a"])(S,P,w,!1,null,null,null),D=O.exports,E=function(){var e=this,t=e.$createElement,n=e._self._c||t;return e.loginError?n("div",{staticClass:"unprotected"},[n("p",[e._v("U hoort hier niet te zijn!")])]):n("div",{staticClass:"unprotected"},[n("h5",[e._v("Gebruik uw emailadres en wachtwoord om in te loggen.")]),n("form",{on:{submit:function(t){return t.preventDefault(),e.callLogin()}}},[n("input",{directives:[{name:"model",rawName:"v-model",value:e.user.email,expression:"user.email"}],attrs:{type:"text",placeholder:"E-mail"},domProps:{value:e.user.email},on:{input:function(t){t.target.composing||e.$set(e.user,"email",t.target.value)}}}),n("input",{directives:[{name:"model",rawName:"v-model",value:e.user.pass,expression:"user.pass"}],attrs:{type:"text",placeholder:"Wachtwoord"},domProps:{value:e.user.pass},on:{input:function(t){t.target.composing||e.$set(e.user,"pass",t.target.value)}}}),n("b-btn",{attrs:{variant:"success",type:"submit"}},[e._v("Inloggen")]),e.error?n("p",{staticClass:"error"},[e._v("De inloggegevens zijn inccorrect")]):e._e()],1)])},j=[],A={name:"login",data:function(){return{user:{email:"",pass:""},loginError:!1,error:!1,errors:[]}},methods:{callLogin:function(){var e=this;this.errors=[],this.$store.dispatch("login",{email:this.user.email,password:this.user.pass}).then(function(){e.$router.push("/controlpanel")}).catch(function(t){e.loginError=!0,e.errors.push(t),e.error=!0})}}},C=A,$=Object(c["a"])(C,E,j,!1,null,null,null),N=$.exports,L=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("h1",[n("b-badge",{attrs:{variant:"info"}},[e._v("Control Panel")])],1),n("router-link",{attrs:{to:"/dienst/Aanmaken"}},[n("h5",[n("b-badge",{attrs:{variant:"primary",id:"dienstAanmakenBtn"}},[e._v("Nieuwe Dienst")])],1)])],1)},R=[],W=n("2f62");r["default"].use(W["a"]);var I=new W["a"].Store({state:{loginSuccess:!1,loginError:!1,userEmail:null,userPass:null},mutations:{login_success:function(e,t){e.loginSuccess=!0,e.userEmail=t.userEmail,e.userPass=t.userPass},login_error:function(e,t){e.loginError=!0,e.userEmail=t.userEmail}},actions:{login:function(e,t){var n=e.commit,r=t.email,o=t.password;return new Promise(function(e,t){console.log("Accessing backend with user: '"+r),g.getSecured(r,o).then(function(t){console.log("Response: '"+t.data+"' with Statuscode "+t.status),200==t.status&&(console.log("Login successful"),n("login_success",{userEmail:r,userPass:o})),e(t)}).catch(function(e){console.log("Error: "+e),n("login_error",{userEmail:r}),t("Invalid credentials!")})})}},getters:{isLoggedIn:function(e){return e.loginSuccess},hasLoginErrored:function(e){return e.loginError},getUserEmail:function(e){return e.userEmail},getUserPass:function(e){return e.userPass}}}),U={name:"controlpanel",data:function(){return{}}},B=U,z=(n("3cf4"),Object(c["a"])(B,L,R,!1,null,null,null)),M=z.exports;r["default"].use(d["a"]);var q=new d["a"]({mode:"history",routes:[{path:"/",component:y},{path:"/login",component:N},{path:"/controlpanel",component:M,meta:{requiresAuth:!0}},{path:"/dienst/:action",component:D,meta:{requiresAuth:!0}},{path:"*",redirect:"/"}]});q.beforeEach(function(e,t,n){e.matched.some(function(e){return e.meta.requiresAuth})?I.getters.isLoggedIn?n():n({path:"/login"}):n()});var T=q,V=n("5f5b");n("f9e3"),n("2dd8");r["default"].config.productionTip=!1,r["default"].use(V["a"]),new r["default"]({router:T,store:I,render:function(e){return e(l)}}).$mount("#app")},"5c0b":function(e,t,n){"use strict";var r=n("5e27"),o=n.n(r);o.a},"5e27":function(e,t,n){},a57d:function(e,t,n){},ad5f:function(e,t,n){e.exports=n.p+"static/img/spring-boot-vuejs-logo.6886ee1f.png"}});
//# sourceMappingURL=app.73ff0f13.js.map