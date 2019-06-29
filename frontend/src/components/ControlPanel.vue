<template>
    <div>
        <!-- In de dienstlist staan niet alleen diensten, maar ook buttons.
        In dit geval worden alle buttons gerelateerd aan de controlpanel getoont -->
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
