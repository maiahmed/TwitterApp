var id = 0 ;
var data;
     const serializeToJSON = str =>
         str.split('&')
           .map(x => x.split('='))
           .reduce((acc, [key, value]) => ({
             ...acc,
             [key]: isNaN(value) ? value : Number(value)
           }), {});


$(document).ready(function () {
        var table = $('#example').DataTable({

            "processing" : true,
            "serverSide" : true,
            "lengthChange": true,
            "pageLength" : 5,
            "searching" : true,
            "paging" : true,
            "pagingType": "full_numbers",
            "lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]],
            "ajax" : {
                url: "/users",
                type:"GET",
                dataSrc : "data",
                "data": function ( d ) {

                }
            },
            "columnDefs": [ {
                        "targets": -1,
                        "data": null,
                         "defaultContent": "<button class='editUser btn btn-primary' data-id='" + id + "' data-toggle='tooltip' title='Edit' data-title='Edit' data-toggle='modal' onclick='update(" + id + ")'><span class='glyphicon glyphicon-pencil'></span></button>"
                    } ],

            "columns":[
                 {"data": "id"},
                {"data": "name"},
                {"data": "tweet"},
                {"data": "update"}
            ]
        });

 $('#example tbody').on( 'click', 'button', function () {
         data = table.row( $(this).parents('tr') ).data();
         id = data.id;
         $('#squarespaceUpdateModal')
            .find('[name="id"]').val(data.id).end()
            .find('[name="name"]').val(data.name).end()
            .find('[name="tweet"]').val(data.tweet).end();
//             $('#formedit').attr("action", "/edit/"+id);
             $('#squarespaceUpdateModal').modal("show");
    } );


        $('#submitButton').click( function(){


//        var formdata = serializeToJSON($('#submitForm').serializeArray());
        var formdata = JSON.parse(JSON.stringify(jQuery('#submitForm').serializeArray())); // store json object
//        var obj = JSON.parse(formdata);

        console.log(formdata);
//        alert('Submit');
            $.ajax({
                url: "/edit/" + id,
                method : "POST",
                data: JSON.stringify(formdata),
                contentType: "application/json",
                dataType: "json",
                success: function(msg) {console.log("-----------------------------"); $('#example').DataTable().ajax.reload();}
            });

                         $('#squarespaceUpdateModal').modal("hide");
                         $('#example').DataTable().ajax.reload();
        });



        // Close Button
        $('#close').click(function(){
              $('#squarespaceUpdateModal').modal("hide");
        });



 });


