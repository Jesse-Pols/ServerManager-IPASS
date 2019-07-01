<template>
    <div class="dienst_workshop">
        <!-- In deze component kunnen diensten worden aangemaakt, gewijzigd of verwijderd. -->
        <!-- Als de component is ingeladen met "Wijzigen" op true, dan wordt alle informatie uit de sessionstorage & database gehaald,
        en ingevuld in het formulier. -->

        <h1 v-if="Aanmaken">Dienst Aanmaken</h1>
        <h1 v-if="Wijzigen">Dienst Wijzigen</h1>

        <input type="text" v-model="dienst.name" placeholder="Naam"><br>
        <textarea placeholder="URL" v-model="dienst.key"></textarea><br><br>

        <h4>Relevant voor:</h4>
        <input type="checkbox" id="s_checkbox" v-model="checkBoxes.Studenten">
        <label for="s_checkbox">&nbsp;Studenten</label><br>
        <input type="checkbox" id="d_checkbox" v-model="checkBoxes.Docenten">
        <label for="d_checkbox">&nbsp;Docenten</label><br>
        <input type="checkbox" id="op_checkbox" v-model="checkBoxes.OP">
        <label for="op_checkbox">&nbsp;Onderwijsondersteunend Personeel</label><br><br>

        <button @click="createDienst()" v-if="Aanmaken">Aanmaken</button>
        <button @click="updateDienst()" v-if="Wijzigen">Wijzigen</button>
        <button @click="showModal = true" v-if="Wijzigen">Verwijderen</button>

        <!-- Een check of de gebruiker zeker weet dat hij de dienst wilt verwijderen. -->
        <modal v-if="showModal" @close="showModal = false">
            <h3 slot="header">
                Weet u het zeker?
            </h3>
            <h5 slot="body">
                <h5>U staat op het punt om de dienst <b>'{{ savedDienst.name }}'</b> te verwijderen.</h5>
            </h5>
            <p slot="footer" id="actual-footer">
                <button class="modal-alert-button" @click="deleteDienst()">
                    Verwijderen
                </button>
                <button class="modal-default-button" @click="showModal = false">
                    Terug
                </button>
            </p>
        </modal>
    </div>
</template>

<script>
import api from './../backend-api'
import storage from './../../tempstore'
import Modal from '@/components/modules/Modal'

export default {
    name: 'dienst_workshop',

    props: {
        Aanmaken: Boolean,
        Wijzigen: Boolean,
        Value: String
    },

    components: {
        Modal
    },

    data () {
        return {
            showModal: false,
            savedDienst: {
                id: 0,
                name: '',
                key: '',
                relevance: ''
            },
            dienst: {
                id: 0,
                name: '',
                key: '',
                relevance: ''
            },
            checkBoxes: {
                Studenten: false,
                Docenten: false,
                OP: false
            }

        }
    },

    mounted: function() {

        // Als de value een waarde heeft, dan is de component een 'Wijzigen' component en moet de form dus vooraf ingevuld worden met de beschikbare data.
        // De meeste data staat opgeslagen in de sessionstorage, behalve de key, oftewel de URL.
        // Deze kan in sommige gevallen geheim zijn, en moet dus altijd uit de database gehaald worden.
        // De gewone data kan worden opgehaald met de naam van de dienst. De key kan opgehaald worden met een ID.

        if (this.Value != null) {

            // Alle diensten worden uit de sessionstorage gehaald.
            var data = storage.get('diensten');
            var obj;

            // Op basis van de naam wordt er gecheckt welk object we nodig hebben.
            for (let i = 0; i < data.length; i++) {
                if (data[i].name == this.Value)
                    obj = data[i];
            }

            // De textboxes worden ingevuld.
            this.dienst.id = obj.id;
            this.dienst.name = obj.name;
            this.dienst.relevance = obj.relevance;

            // De checkboxes worden eventueel aangevinkt.`
            if (obj.relevance != null) {
                if (obj.relevance.includes('Studenten')) this.checkBoxes.Studenten = true;
                if (obj.relevance.includes('Docenten')) this.checkBoxes.Docenten = true;
                if (obj.relevance.includes('OP')) this.checkBoxes.OP = true;
            }

            // De key wordt uit de database gehaald.
            api.getKeyById(obj.id)
            .then(response => {
                this.dienst.key = response.data;
            })
            .catch(err => {
                console.log(err);
            });

            // this.dienst veranderdt op basis van wat er in de tekstvelden staat.
            // this.savedDienst kan niet veranderen.
            this.savedDienst = this.dienst;

        }
    },

    methods: {
        checkRelevance: function() {

            let addToString = function(str, x) {
                if (str != "") str += ",";
                str += x;
                return str;
            }

            let checkThis = function(rel, check) {
                if (check.Studenten) rel = addToString(rel, 'Studenten');
                if (check.Docenten) rel = addToString(rel, 'Docenten');
                if (check.OP) rel = addToString(rel, 'OP');
                return rel;
            }

            this.dienst.relevance = checkThis("", this.checkBoxes);

        },

        createDienst: function() {

            this.checkRelevance();         
            if (this.dienst.relevance != "") {
                api.createDienstWithRelevance(this.dienst)
                .then(response => {
                    console.log(response);
                    this.$router.push('/controlpanel')
                })
                .catch(err => {
                    console.log(err);
                })
            } else {
                api.createDienst(this.dienst)
                .then(response => {
                    console.log(response);
                    this.$router.push('/controlpanel')
                })
                .catch(err => {
                    console.log(err);
                })
            }
        },

        updateDienst: function() {
            
            if (this.dienst.name == '' || this.dienst.key == '') {
                alert("Laat geen velden leeg alstublieft.")
                return;
            }

            this.checkRelevance();
            if (this.dienst.relevance == "") this.dienst.relevance = "None";
            api.updateDienst(this.savedDienst.id, this.dienst)
            .then(response => {
                console.log(response);
                this.$router.push('/controlpanel');
            })
            .catch(err => {
                console.log(err);
            })
        },

        deleteDienst: function() {
            api.deleteDienst(this.savedDienst.id)
            .then(response => {
                console.log(response);
                this.$router.push('/controlpanel')
            })
            .catch(err => {
                console.log(err);
            })
            this.showModal = false;
        }
    }

}
</script>

<style scoped>

.dienst-info {
    width: 100%;
}

.left {
    float: left;
}

.right {
    float: right;
}

.dienst_workshop textarea {
    width: 20em;
}

#actual-footer {
    width: 100%;
}

</style>    