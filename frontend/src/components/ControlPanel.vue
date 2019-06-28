<template>
    <div>
        <DienstList ControlPanel></DienstList>
    </div>
</template>

<script>
import api from './backend-api'
import store from './../store'
import DienstList from '@/components/modules/DienstList'

export default {
    name: 'controlpanel',

    components: {
        DienstList
    },

    data () {
        return {
        }
    }

}
</script>

<style>

#logsBtn {
    padding: 10px;
}

#dienstAanmakenBtn {
    padding: 10px;
}

</style>
