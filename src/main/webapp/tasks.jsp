<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Task list</title>
<link rel="stylesheet" type="text/css" href="resources/styles/tasks.css"	media="screen" />
<script src="resources/scripts/jquery-2.0.3.js"></script>
<script src="resources/scripts/jquery-tmpl.js"></script>
<script src="resources/scripts/jquery.validate.js"></script>
<script src="resources/scripts/jquery-serialization.js"></script>
<script src="resources/scripts/tasks-controller.js"></script>
<script src="resources/scripts/date.js"></script>
<script src="resources/scripts/loadTable.js"></script>
</head>
<body>
	<header>
		<span>Task list</span>
	</header>
	<main  id="taskPage">
	<section id="taskCreation" class="not">
		<form id="taskForm">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label>Task</label>
				<input type="text" required="required"
					name="task" class="large form-control" placeholder="Breakfast at Tiffanys" maxlength="200"  />
			</div>
			<!-- The Priority ratings that is added to the task table  -->
			<div class="form-group">
				<label>Priority</label> <select name="priority" class="form-control">
				<option value="1">Urgent</option>
				<option value="2">High</option>
				<option value="3">Medium</option>
				<option value="4">Low</option>
			</select>
			</div>
			<div class="form-group">
				<label>Required by</label> <input   type="date" required="required"
					name="requiredBy" class="form-control" />
			</div>
			<div class="form-group">
				<label>Category</label> <select name="category" class="from-control">
					<option value="Personal">Personal</option>
					<option value="Work">Work</option>
				</select>
			</div>
			<div class="form-group">
				<label>Fot Team</label>
				<select class="form-control">
					<c:forEach var="team"
				</select>
			</div>
			<nav>
				<a href="#" id="saveTask">Save task</a>    <!-- https://stackoverflow.com/questions/4855168/what-is-href-and-why-is-it-used -->
				<a href="#" id="clearTask">Clear task</a>
			</nav>
		</form>
	</section>
	<section>
		<table id="tblTasks">
			<colgroup>
				<col width="30%">
				<col width="15%">
				<col width="15%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th>Name</th>
					<th>Due</th>
					<th>Category</th>
					<th>Priority</th>
					<th>Actions</th>

				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<nav>
			<a href="#" id="btnAddTask">Add task</a>
			<a href="#" id="btnRetrieveTasks">Retrieve tasks from server</a>
		</nav>
	</section>
	</main>
	<footer>You have <span id="taskCount"></span>tasks</footer>
</body>
<div id="taskRow">

</div>

<%--<script id="taskRow" type="text/x-jQuery-tmpl">
<tr>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${task}</td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}><time datetime="${requiredBy}">${requiredBy}</time></td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${category}</td>
	<td {{if complete == true}}class="taskCompleted"{{/if}}>${priority}</td>
	<td>
		<nav>
			{{if complete != true}}			
				<a href="#" class="editRow" data-task-id="${id}">Edit</a>
				<a href="#" class="completeRow" data-task-id="${id}">Complete</a>
			{{/if}}
			<a href="#" class="deleteRow" data-task-id="${id}">Delete</a>
		</nav>
	</td>
</tr>
</script>--%>


</html>
