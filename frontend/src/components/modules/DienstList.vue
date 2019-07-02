<template>
<div id="dienst_list">
    <!-- Dit component haalt alle diensten uit de database, en toont ze in een overzichtelijke lijst. -->
    <!-- Daarnaast wordt er bepaalt wat voor lijst het moet worden. -->
    <!-- Als de lijst op de homepagina moet komen, dan moeten er sorteerknoppen bij komen te staan. -->
    <!-- Als de lijst op het controlpanel moet komen, dan moeten er controlpanel relevante knoppen bij komen te staan -->
    <!-- De diensten in de controlpanel-lijst zijn klikbaar, en verwijzen naar de dienstworkshop. -->

    <!-- BUTTONS -->
    <div class="buttons" v-if="Homepage">
        <input type="button" @click="setSort('Studenten')" value="Studenten">
        <input type="button" @click="setSort('Docenten')" value="Docenten">
        <input type="button" @click="setSort('OP')" value="Onderwijsondersteunend Personeel">
        <input type="button" @click="check()" id="actionButton" value="Check">
    </div>
    <div class="buttons" v-if="ControlPanel">
        <router-link :to="{ name: 'workshop_aanmaken', params: { wType: 'Dienst', wAction: 'Aanmaken' }}"><input type="button" value="Nieuwe Dienst"></router-link>
         <!-- <router-link to="/logs"><input type="button" value="Logs"></router-link> -->
        <input type="button" @click="logOut()"  value="Uitloggen">
    </div>

    <!-- LIST -->
    <div v-for="dienst in diensten" v-bind:key="dienst.id">
        <div class="card">
            <div class="card-body">

                <!-- Home Page -->
                <div class="homepageContent" v-if="Homepage">
                    <h6 class="card-title">
                        {{ dienst.name }}
                    </h6>
                    <h6 class="card-text">
                        {{ dienst.status }}
                    </h6>
                </div>

                <!-- Control Panel -->
                <div class="controlPanelContent" v-if="ControlPanel">
                    <router-link :to="{ name: 'workshop_wijzigen', params: { wType: 'Dienst', wAction: 'Wijzigen', wValue: dienst.name }}">
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
import cookies from './../../cookies'
import storage from './../../tempstore'

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

            // Alle diensten worden uit de database gehaald, en de urls worden gecheckt op beschikbaarheid.
            // Alle diensten worden in het this.diensten array gestopt, en automatisch getoond in de template.
            // Deze functie kan wat langzamer zijn, afhankelijk van hoe snel de servers reageren op het request.
            // Daarom worden de diensten nog extra opgeslagen in de sessionstorage, zodat we niet telkens deze functie hoeven uit te voeren.
            // De key wordt niet opgeslagen in this.diensten, of in de sessionstorage.

            // Allereerst wordt de huidige dienstenlijst geleegd.
            this.diensten = [];

            api.getDiensten ()
            .then(response => {
                return response.data.Diensten;
            })
            .then(data => {
                for (let i = 0; i < data.length; i++) {
                    let obj = data[i];
                    this.diensten.push({
                        id: obj.id,
                        name: obj.name,
                        relevance: obj.relevance,
                        status: obj.status,
                        score: 0
                    });
                }
            })
            .then(data => {
                // De dienstenlijst wordt na het uitvoeren nog even gesorteerd.
                this.sort(this.diensten);
                storage.set('diensten',this.diensten);
            })
            .catch(err => {
                console.log(err);
            })
        },

        sort: function(data) {

            let switchTo = function (x) {

                // Met de score wordt bepaalt welke diensten bovenaan de lijst komen te staan.
                for (let i = 0; i < data.length; i++) {

                    let obj = data[i];
                    obj.score = 0;

                    // Objecten welke relevant zijn voor groep X krijgen een score van 2.
                    // Objecten welke geen relevantie hebben zijn even relevant voor iedereen, en krijgen altijd een score van 1.
                    // Objecten welke relevant zijn voor iemand, maar niet voor groep X, krijgen een score van 0.

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
        },

        logOut: function() {
            cookies.setCookie("loggedIn");
            this.$router.push('/login')
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
    background-color:#f8f8f8;
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
    border-color:#626262;
    background-color:#f8f8f8;
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
