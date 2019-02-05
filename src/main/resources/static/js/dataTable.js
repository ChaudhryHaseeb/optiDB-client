$(document).ready(function() {
    var url = "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json";
    $('#table_id').DataTable( {
        "language": {
            "url": url
        }
    } );
} );