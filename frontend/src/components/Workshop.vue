<template>
    <div class="workshop">
        <DienstWorkshop Aanmaken v-if="Dienst.Aanmaken"></DienstWorkshop>
        <DienstWorkshop Wijzigen v-if="Dienst.Wijzigen"></DienstWorkshop>
        <DienstWorkshop Verwijderen v-if="Dienst.Verwijderen"></DienstWorkshop>
    </div>
</template>

<script>
import api from './backend-api';
import DienstWorkshop from '@/components/modules/DienstWorkshop'

export default {
    name: 'workshop',

    mounted: function() {
        console.log(this.$route.params.action);
        console.log(this.$route.params.type);

        var type = this.$route.params.type;
        var action = this.$route.params.action;

        if (type == 'Dienst') {
            if (action == 'Aanmaken') this.Dienst.Aanmaken = true; else this.Dienst.Aanmaken = false;
            if (action == 'Wijzigen') this.Dienst.Wijzigen = true; else this.Dienst.Wijzigen = false;
            if (action == 'Verwijderen') this.Dienst.Verwijderen = true; else this.Dienst.Verwijderen = false;
        }
    },

    components: {
        DienstWorkshop
    },
    
    data () {
        return {
            Dienst: {
                Aanmaken: true,
                Wijzigen: false,
                Verwijderen: false
            }
        }
    }
}
</script>

<style>

</style>
