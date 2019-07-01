<template>
    <h1>Hree</h1>
</template>

<script>
export default {
    name: 'log',

    created: function() {
        console.log(this.$route.params.lId);
    }

}
</script>

<style>

</style>
