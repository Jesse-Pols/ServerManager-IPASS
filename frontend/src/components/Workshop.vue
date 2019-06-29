<template>
    <div class="workshop">
        <!-- Workshop is een verzamelplaats van alle gespecificeerde workshops, waar bijvoorbeeld crud handelingen mee worden uitgevoerd -->
        <DienstWorkshop v-bind:Wijzigen="Dienst.Wijzigen" v-bind:Aanmaken="Dienst.Aanmaken" v-bind:Value="Dienst.Value" v-if="Dienst.Aanmaken"></DienstWorkshop>
        <DienstWorkshop v-bind:Wijzigen="Dienst.Wijzigen" v-bind:Aanmaken="Dienst.Aanmaken" v-bind:Value="Dienst.Value" v-if="Dienst.Wijzigen"></DienstWorkshop>
    </div>
</template>

<script>
import api from './backend-api';
import DienstWorkshop from '@/components/modules/DienstWorkshop'

export default {
    name: 'workshop',      

    data () {
        return {
            Dienst: {
                Aanmaken: true,
                Wijzigen: false,
                Value: null
            }
        }
    },

    created: function() {
        // Deze code draait nadat de data is ingeladen, maar nog voor dat de template is gerendered.
        // Hier wordt bepaald welke workshop er getoont moet gaan worden, op basis van het type, de actie en in sommige gevallen de value.

        var type = this.$route.params.wType;
        var action = this.$route.params.wAction;
        var value = this.$route.params.wValue;

        if (type == 'Dienst') {
            this.Dienst.Aanmaken = (action == 'Aanmaken' ? true : false);
            this.Dienst.Wijzigen = (action == 'Wijzigen' ? true : false);
            if (value != null) this.Dienst.Value = value;
        }

    },

    components: {
        DienstWorkshop
    }
}
</script>

<style>

</style>
