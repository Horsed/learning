var TasksView = {};

(function() {
    TasksView = Backbone.View.extend({
        	
    	el: "body",
    	
    	title: "input#title",
    	
    	tasks: "ul.tasks",
    	
    	events: {
    		"click button#add": "add",
    		"keypress input#title": "add",
    		"click button#sum": "sum"
    	},
    	
    	initialize: function(){
    		_.bindAll(this, 'render', 'add', 'sum', 'addToCollection');
    		Util.vent.bind("deltaChanged", this.sum);
    
    		this.collection = new Tasks();
    		this.collection.bind("add", this.appendTask, this);
    
    		this.render();
    	},
    		
    	render: function() {
            $(this.el).append(ich.controlCenter());
            $(this.el).append(ich.tasks());
            
    		$(this.tasks, this.el).sortable();
    		
    		$('button#add', this.el).button({
    			icons: {primary: "ui-icon-document"}, text: false
    		});
    		
    		$('button#sum', this.el).button({
    			icons: {primary: "ui-icon-clock"}, text: false
    		});
    		
    		_(this.collection.models).each(function(task) {
    			appendTask(task);
    		}, this);
    		
    		return this;
    	},
    	
    	appendTask: function(task) {
    		var taskView = new TaskView({
    			model: task,
    			collection: this.collection
    		});
            var li = taskView.render().el;
    		$(this.tasks, this.el).append(li);
    	},
    	
    	add: function(e) {
        	return Util.keyboard.executeOnEnter(e, this.addToCollection); 
    	},
        
        addToCollection: function() {
            this.collection.add({
            	title: $(this.title, this.el).val()
    		});
    		$(this.title, this.el).val('');
        },
    				
    	sum: function() {
    		$('label#sum', this.el).text(this.collection.getSum() + " h");
    	}
    });
    
    $(document).ready(function() {
    	window.AppView = new TasksView;
    });
}())