// Alle basis functies om te kunnen werken met de sessionStorage.
export default {

    set(key, value) {
        sessionStorage.setItem(key, JSON.stringify(value));
    },

    get(key) {
        return JSON.parse(sessionStorage.getItem(key));
    },

    delete(key) {
        sessionStorage.removeItem(key);
    },

    clear() {
        sessionStorage.clear();
    }
    
}