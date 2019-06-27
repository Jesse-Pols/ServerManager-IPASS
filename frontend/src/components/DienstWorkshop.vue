<template>
    <div class="dienst_workshop">
        <!-- <h1>Dienst {{ plannedAction }}</h1> -->

           <h1> Dienst {{ $route.params.action }} </h1>
        
        <input type="text" v-model="dienst.name" placeholder="Naam">
        <input type="text" v-model="dienst.key" placeholder="URL">

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
            }
        }
    },

    methods: {
        createDienst: function() {

            console.log(this.dienst);

            api.createDienst(this.dienst)
            .then(response => {
                console.log(response);

            })
            .catch(e => {
                console.log(e);
            })

        }
    }
}
</script>

<style>

</style>
