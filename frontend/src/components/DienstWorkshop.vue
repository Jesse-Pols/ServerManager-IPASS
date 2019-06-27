<template>
    <div class="dienst_workshop">
        <!-- <h1>Dienst {{ plannedAction }}</h1> -->

           <h1> Dienst {{ $route.params.action }} </h1><br>
        
        <input type="text" v-model="dienst.name" placeholder="Naam">
        <input type="text" v-model="dienst.key" placeholder="URL"><br><br>

        <h4>Relevant voor:</h4>
        <input type="checkbox" id="studentenCheckbox" v-model="Studenten">
        <label for="studentenCheckbox">&nbsp;Studenten</label><br>
        <input type="checkbox" id="docentenCheckbox" v-model="Docenten">
        <label for="docentenCheckbox">&nbsp;Docenten</label><br>
        <input type="checkbox" id="opCheckbox" v-model="OP">
        <label for="opCheckbox">&nbsp;Onderwijsondersteunend Personeel</label><br><br>

        <button @click="createDienst()" v-if="actions.create">Aanmaken</button>
        <button @click="deleteDienst()" v-if="actions.change">Wijzigen</button>
        <button @click="changeDienst()" v-if="actions.delete">Verwijderen</button>

    </div>
</template>

<script>
import api from './backend-api';

export default {
    name: 'dienst_workshop',
   
    mounted: function() {

        if (this.$route.params.action == "Wijzigen"){
            this.actions.create = false;
            this.actions.change = true;
            this.actions.delete = false;
        }

        else if (this.$route.params.action == "Verwijderen"){ 
            this.actions.create = false;
            this.actions.change = false;
            this.actions.delete = true;
        }
        
        else {
            this.actions.create = true;
            this.actions.change = false;
            this.actions.delete = false;
        }

    },

    data () {
        return {
            dienst: {
                id: 0,
                name: '',
                key: '',
                relevance: ''
            },
            actions: {
                create: true,
                change: false,
                delete: false
            },
            Studenten: false,
            Docenten: false,
            OP: false
        }
    },

    methods: {
        createDienst: function() {

            // Check if all fields are filled in
            // Check if any one of the checkboxes is filled
            // Send request to api
            this.dienst.relevance = '';
            var self = this;
            var addToString = function(str, x){
                if (str != '')
                    str += ',';
                str += x;
                return str;
            }

            if (self.dienst.name == '' || self.dienst.key == '') {
                alert("Vul een naam en een URL in.");
                return;
            }

            if (self.Studenten)
                self.dienst.relevance = addToString(self.dienst.relevance, 'Studenten');
            if (self.Docenten)
                self.dienst.relevance = addToString(self.dienst.relevance, 'Docenten');
            if (self.OP)
                self.dienst.relevance = addToString(self.dienst.relevance, 'OP');

            console.log(self.dienst.relevance);

            if (self.dienst.relevance == '') {
                api.createDienst(self.dienst)
                .then(response => {
                    console.log(response);
                })
                .then(() => {
                    this.$router.push('/controlpanel')
                });
            } else {
                api.createDienstWithRelevance(self.dienst)
                .then(response => {
                    console.log(response);
                })
                .then(() => {
                    this.$router.push('/controlpanel')
                });
            }
                
                


/*

            
            if (this.dienst.relevance == '') {
            	api.createDienst(this.dienst)
            	.then(response => {
            		console.log(response);
            	});               
            } else {
            	api.createDienstWithRelevance(this.dienst)
            	.then(response => {
            		console.log(response);
            	});
            }
            */

        }
    }
}
</script>

<style>

</style>
