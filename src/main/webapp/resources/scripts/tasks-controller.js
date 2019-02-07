

tasksController = function() {
	
	function errorLogger(errorCode, errorMessage) {
		console.log(errorCode +':'+ errorMessage);
	}
	
	var taskPage;
	var initialised = false;

    /**
	 * makes json call to server to get task list.
	 * currently just testing this and writing return value out to console
	 * 111917kl
     */
	function retrieveTasksServer() {
        $.ajax("tasks", {
            "type": "get",
			dataType: "json"
            // "data": {
            //     "first": first,
            //     "last": last
            // }
        }).done(displayTasksServer.bind()); //need reference to the tasksController object
    }
    function loadTeamFromServer(){
        $.ajax("teams",{
            "type":"get",
            dataType: "json"
        }).done(loadTeam);
	}

    const category= $('#category');
    category.change(function () {
        const team= $('#teamDiv');
        if(category.val() === "Personal") team.css('display','none');
        else team.css('display','block');
    });
    function loadTeam(teams) {
        const forTeam=$('#team');
        forTeam.empty();
        console.log(teams);
        forTeam.append($('<option>',{'value':'select','text':'Select Team'}));
        $.each(teams, function(idx,team){
                forTeam.append($('<option>',{ "value":+teams[idx].teamId, 'text':teams[idx].name}))
            }
        );
    }
    function retrieveAllUserTasksServer() {
        $.ajax("allTasks", {
            "type": "get",
            dataType: "json"
            // "data": {
            //     "first": first,
            //     "last": last
            // }
        }).done(displayTasksServer.bind()); //need reference to the tasksController object
    }

    function saveToServer(task){
   $.ajax("tasks", {
            "type": "post",
             "data":task
        }).done(displayTasksServer.bind());
	}
    retrieveTasksServer();
    /**
	 * 111917kl
	 * callback for retrieveTasksServer
     * @param data
     */
    function displayTasksServer(data) { //this needs to be bound to the tasksController -- used bind in retrieveTasksServer 111917kl
    	console.log(data);
        tasksController.loadServerTasks(data);
    }
	
	function taskCountChanged() {
		var count = $(taskPage).find( '#tblTasks tbody tr').length;
		$('footer').find('#taskCount').text(count);
	}
	
	function clearTask() {
		$(taskPage).find('form').fromObject({});
	}
	
	function renderTable() {
		$.each($(taskPage).find('#tblTasks tbody tr'), function(idx, row) {

			var due = Date.parse($(row).find('[datetime]').text());
			if (due.compareTo(Date.today()) < 0) {
				$(row).addClass("overdue");
			} else if (due.compareTo((2).days().fromNow()) <= 0) {
				$(row).addClass("warning");
			}
		});
	}
	
	return { 
		init : function(page, callback) { 
			if (initialised) {
				callback()
			} else {
				taskPage = page;
				storageEngine.init(function() {
					storageEngine.initObjectStore('task', function() {
						callback();
					}, errorLogger) 
				}, errorLogger);	 				
				$(taskPage).find('[required="required"]').prev('label').append( '<span>*</span>').children( 'span').addClass('required');
				$(taskPage).find('tbody tr:even').addClass('even');
				
				$(taskPage).find('#btnAddTask').click(function(evt) {
					evt.preventDefault();
                    loadTeamFromServer();
					$(taskPage).find('#taskCreation').removeClass('not');
                    $('#creteUser').addClass('not');
					$('#ManageTeam').addClass('not');
				});

				$(taskPage).find('#btnAddTeam').click(function(evt) {
					evt.preventDefault();
					$(taskPage).find('#teamManage').removeClass('not');
					$('#creteUser').addClass('not');
					$('#tblUser').addClass('not');
				});

                /**	 * 11/19/17kl        */
                $(taskPage).find('#btnRetrieveTasks').click(function(evt) {
                    evt.preventDefault();
                    console.log('making ajax call');
                    retrieveAllUserTasksServer();
					$('#creteUser').addClass('not');
                });

				$(taskPage).find('#tblTasks tbody').on('click', 'tr', function(evt) {
					$(evt.target).closest('td').siblings().andSelf().toggleClass('rowHighlight');
				});	
				
				$(taskPage).find('#tblTasks tbody').on('click', '.deleteRow', 
					function(evt) { 					
						storageEngine.delete('task', $(evt.target).data().taskId, 
							function() {
								$(evt.target).parents('tr').remove(); 
								taskCountChanged();
							}, errorLogger);
						
					}
				);
				
				$(taskPage).find('#tblTasks tbody').on('click', '.editRow', 
					function(evt) { 
						$(taskPage).find('#taskCreation').removeClass('not');
						storageEngine.findById('task', $(evt.target).data().taskId, function(task) {
							$(taskPage).find('form').fromObject(task);
						}, errorLogger);
					}
				);
				
				$(taskPage).find('#clearTask').click(function(evt) {
					evt.preventDefault();
					clearTask();
				});
				
				/*$(taskPage).find('#tblTasks tbody').on('click', '.completeRow', function(evt) {
					storageEngine.findById('task', $(evt.target).data().taskId, function(task) {
						task.complete = true;
						storageEngine.save('task', task, function() {
							tasksController.loadTasks();
						},errorLogger);
					}, errorLogger);
				});*/
				
				$(taskPage).find('#saveTask').click(function(evt) {
					evt.preventDefault();
					if ($(taskPage).find('form').valid()) {
						var task = $(taskPage).find('#taskForm').toObject();
						saveToServer(task);
						/*storageEngine.save('task', task, function() {
							$(taskPage).find('#tblTasks tbody').empty();
							tasksController.loadTasks();
							clearTask();
							$(taskPage).find('#taskCreation').addClass('not');
						}, errorLogger);*/
					}
				});
				initialised = true;
			}
		},
        /**
		 * 111917kl
		 * modification of the loadTasks method to load tasks retrieved from the server
         */
		loadServerTasks: function(tasks) {
			console.log("this is task from server"+tasks);
            $(taskPage).find('#tblTasks tbody').empty();
            $.each(tasks, function (index, task) {
                if (!task.complete) {
                    task.complete = false;
                }
                console.log("this is task from the loop "+task);
                $('#taskRow2').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
                taskCountChanged();
                console.log('about to render table with server tasks');
                //renderTable(); --skip for now, this just sets style class for overdue tasks 111917kl
            });
		}/*,
		loadTasks : function() {
			$(taskPage).find('#tblTasks tbody').empty();
			storageEngine.findAll('task', function(tasks) {
				console.log('this is tasks from the database' + tasks);
				tasks.sort(function(o1, o2) {
					return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
					//I changed from sorting due date  to priority by sort to the new added
                    //  return ((o1.priority)>(o2.priority))? 1 :((o1.priority)<(o2.priority))? -1:0;

					/!*if(o1.priority>o2.priority) return 1;
					else if(o1.priority<o2.priority) return -1;
					else return Date.parse(o1.requiredBy).compareTo(Date.parse(o2.requiredBy));
*!/

				});
				$.each(tasks, function(index, task) {
					if (!task.complete) {
						task.complete = false;
					}
					$('#taskRow').tmpl(task).appendTo($(taskPage).find('#tblTasks tbody'));
					taskCountChanged();
					renderTable();
				});
			}, errorLogger);
		} */
	} 
}();
$(function (){
    $('#sortByPriority').click(sortTable);
    function sortTable() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("tblTasks");
        switching = true;
        /* Make a loop that will continue until
        no switching has been done: */
        while (switching) {
            // Start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /* Loop through all table rows (except the
            first, which contains table headers): */
            for (i = 1; i < (rows.length - 1); i++) {
                // Start by saying there should be no switching:
                shouldSwitch = false;
                /* Get the two elements you want to compare,
                one from current row and one from the next: */
                x = rows[i].getElementsByTagName("TD")[3];
                y = rows[i + 1].getElementsByTagName("TD")[3];
                // Check if the two rows should switch place:
                if (getIntegerValue(x.innerHTML) > getIntegerValue(y.innerHTML)) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /* If a switch has been marked, make the switch
                and mark that a switch has been done: */
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }

    }

    function getIntegerValue(priority) {
		priority=priority.toLowerCase();
		if(priority === 'high') return 1;
		else if(priority === 'medium') return 2;
		else  return 3;
    }
} )


