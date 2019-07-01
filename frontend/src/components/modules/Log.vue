<template>
    <h1>Hree</h1>
</template>

<script>
export default {
    name: 'log',

    data () {
        return {
            dienst: {
                id: 0,
                logs: []
            }
        }
    },

    created: function() {
        this.dienst.id = this.$route.params.lId;
    },

    mounted: function() {
        this.getLogsById();
    },

    methods: {

        getLogsById: function() {

            console.log("GetLogsById");

        }

    }

}
</script>

<style>

</style>
