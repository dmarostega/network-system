<input id="ip" value="8.8.8.8">
<button onclick="ping()">Ping</button>
<div id="response-target">

</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
function ping() {
  let ip = $("#ip").val();
  let reponseTarget = $("#response-target");
    console.log("IP a enviar:", ip);  // ✅ ver no console do navegador
  $.post("retrieve-ping-json.php", {
    action: "ping",
    target: ip
  }, function(resp) {
        console.log("Resposta do PHP:", resp);  // ✅ ver no console

        reponseTarget.html('');
        reponseTarget.html(resp);
  }, 'json')
  .done(function() {
    alert( "second success" );
  })
  .fail(function(response) {
    console.log("Error> " , response);
    alert( "error with response", response );
        alert("Falha na requisição");
  })
  .always(function() {
    alert( "finished" );
  });
}
</script>
