import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Workshop from '@/components/Workshop'
import Login from '@/components/Login'
import ControlPanel from '@/components/ControlPanel'

import cookies from './cookies';

Vue.use(Router);

// Elke pagina krijgt een eigen route. Door deze route aan te roepen ga je naar de betreffende pagina.
const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/login', component: Login },
        { path: '/controlpanel', component: ControlPanel, meta: { requiresAuth: true }},
        {
            path: '/workshop/:wType/:wAction/:wValue', 
            component: Workshop,
            meta: { requiresAuth: true },
            name: 'workshop_wijzigen'
        },
        {
            path: '/workshop/:wType/:wAction',
            component: Workshop,
            meta: { requiresAuth: true },
            name: 'workshop_aanmaken'
        },
        { path: '*', redirect: '/' }
    ]
});

// Als je een route aanroept wordt er eerst gecheckt of je ingelogd bent. Zo niet dan wordt je in sommige gevallen doorverwezen naar de inlog pagina's.
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // Check if logged in
        if (!cookies.checkLoggedIn()) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next();
    }
});

export default router;