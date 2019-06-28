<template>
<div id="dienst_list">
    <div class="buttons" v-if="Homepage">
        <input type="button" @click="setSort('Studenten')" id="regButton" value="Studenten">
        <input type="button" @click="setSort('Docenten')" id="regButton" value="Docenten">
        <input type="button" @click="setSort('OP')" id="regButton" value="Onderwijsondersteunend Personeel">
        <input type="button" @click="check()" id="actionButton" value="Check">
    </div>
    <div class="buttons" v-if="ControlPanel">
        <router-link to="/workshop/Dienst/Aanmaken"><input type="button" value="Nieuwe Dienst"></router-link>
        <router-link to="/logs"><input type="button" value="Logs"></router-link>
    </div>
    <div v-for="dienst in diensten" v-bind:key="dienst.id">
        <div class="card">
            <div class="card-body">
                <div class="homepageContent" v-if="Homepage">
                    <h6 class="card-title">
                        {{ dienst.name }}
                    </h6>
                    <h6 class="card-text">
                        {{ dienst.status }}
                    </h6>
                </div>
                <div class="controlPanelContent" v-if="ControlPanel">
                    <router-link to="/dienst/Aanmaken">
                        <h6 class="card-title">
                            {{ dienst.name }}
                        </h6>
                        <h6 class="card-text">
                            {{ dienst.status }}
                        </h6>
                    </router-link>                    
                </div>
            </div>
        </div>
    </div>
</div>    
</template>

<script>
import api from './../backend-api.js'

export default {
    name: 'dienst_list',

    props: {
        Homepage: Boolean,
        ControlPanel: Boolean
    },

    mounted: function() {
        this.check();
    },

    data () {
        return {
            diensten: [],
            sortBy: 'Studenten'
        }
    },

    methods: {
        check: function() {
            
            // Empty the diensten list, so it can be refilled later
            this.diensten = [];

            api.status()
            .then(response => {
                return response.data.Diensten;
            })
            .then(data => {
                // GET Request succesful
                for (let i = 0; i < data.length; i++) {
                    let obj = data[i];
                    this.diensten.push({
                        id: obj.dienstId,
                        key: obj.key,
                        name: obj.name,
                        relevance: obj.relevance,
                        status: obj.status,
                        score: 0
                    });
                }
            })
            .then(data => {
                this.sort(this.diensten);
            })
            .catch(err => {
                //GET Request failed
                console.log(err);
            })
        },

        sort: function(data) {

            let switchTo = function (x) {

                for (let i = 0; i < data.length; i++) {

                    let obj = data[i];
                    obj.score = 0;

                    // Objects who are relevant to group x get a score of 2.
                    // Objects who have no relevancy are equally relevant to all, and get a score of 1.
                    // Objects who are relevant to someone, but not to group x, get a score of 0.

                    if (obj.relevance == null)
                        obj.score += 1;

                    if (x != 'None' && obj.relevance != null)
                        obj.score += (obj.relevance.includes(x)) * 2;

                }

            }

            switchTo(this.sortBy);

            this.diensten.sort(function(a, b) {
                return (a.score-b.score) * -1;
            });

        },

        setSort: function(id) {
            this.sortBy = id;
            this.check();
        }
    }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.card-title, .card-text {
    color: #000;
    font-family:'Roboto',sans-serif;
}

.card:hover {
    text-shadow: 0 0 2em rgba(255,255,255,1);
    border-color:#a2a2a2;
    background-color:#f6f6f6;
}

#dienst_list .buttons input[type=button] {
    display:inline-block;
    padding:0.46em 2.6em;
    border:1px solid #000000;
    margin:0 0.2em 0.2em 0;
    border-radius:0.12em;
    box-sizing: border-box;
    text-decoration:none;
    font-family:'Roboto',sans-serif;
    font-weight:300;
    color:#000000;
    text-shadow: 0 0.04em 0.04em rgba(0,0,0,0.35);
    background-color:#FFF;
    text-align:center;
    transition: all 0.15s;
}

#dienst_list .buttons input[type=button]:hover {
    text-shadow: 0 0 2em rgba(255,255,255,1);
    border-color:#a2a2a2;
}
@media all and (max-width:30em) {
    #dienst_list .buttons input[type=button] {
        display: block;
        margin:0.4em auto;
    }
}

#dienst_list .buttons #actionButton {
    background-color: #FFCD00;
}

</style>
