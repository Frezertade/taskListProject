$(function () {
    /*$("#taskRow").html("<tr>\n" +
        "\t<td {{if complete == true}}class=\"taskCompleted\"{{/if}}>${task}</td>\n" +
        "\t<td {{if complete == true}}class=\"taskCompleted\"{{/if}}><time datetime=\"${requiredBy}\">${requiredBy}</time></td>\n" +
        "\t<td {{if complete == true}}class=\"taskCompleted\"{{/if}}>${category}</td>\n" +
        "\t<td {{if complete == true}}class=\"taskCompleted\"{{/if}}>${priority}</td>\n" +
        "\t<td>\n" +
        "\t\t<nav>\n" +
        "\t\t\t{{if complete != true}}\t\t\t\n" +
        "\t\t\t\t<a href=\"#\" class=\"editRow\" data-task-id=\"${id}\">Edit</a>\n" +
        "\t\t\t\t<a href=\"#\" class=\"completeRow\" data-task-id=\"${id}\">Complete</a>\n" +
        "\t\t\t{{/if}}\n" +
        "\t\t\t<a href=\"#\" class=\"deleteRow\" data-task-id=\"${id}\">Delete</a>\n" +
        "\t\t</nav>\n" +
        "\t</td>\n" +
    "</tr>");*/




    function initScreen() {
        $(document).ready(function() {
            tasksController.init($('#taskPage'), function() {
                tasksController.loadTasks();
            });
        });
    }
    if (window.indexedDB) {
        console.log("using indexedDB 111917kl");
        $.getScript( "resources/scripts/tasks-indexeddb.js" )
            .done(function( script, textStatus ) {
                initScreen();
            })
            .fail(function( jqxhr, settings, exception ) {
                console.log( 'Failed to load indexed db script' );
            });
    } else if (window.localStorage) {
        console.log("using webstorage 111917kl");
        $.getScript( "resources/scripts/tasks-webstorage.js" )
            .done(function( script, textStatus ) {
                initScreen();
            })
            .fail(function( jqxhr, settings, exception ) {
                console.log( 'Failed to load web storage script' );
            });
    }

});