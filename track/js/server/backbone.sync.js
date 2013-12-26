Backbone.sync = function(method, model, options) {

    var response,    
        store = new Store();

    switch (method) {
        case "read":
            response = model.id ? store.find(model) : store.findAll();
            break;
        case "create":
            response = store.create(model);
            break;
        case "update":
            response = store.update(model);
            break;
        case "delete":
            response = store.destroy(model);
            break;
    }

    if(response) {
        options.success(response);
    } else {
        options.error("Record not found");
    }
};