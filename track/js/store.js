var Store = {
    find: function(model) {
        return localStorage.getItem(model.cid);
    },
    
    findAll: function() {
        return localStorage.getItem("tracks");
    },
    
    create: function(model) {
        localStorage.setItem(model.cid, model.toJSON());
    },
    
    update: function(model) {
    },
    
    destroy: function(model) {
    }
};