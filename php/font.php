<input id="ip" value="8.8.8.8">
<button onclick="ping()">Ping</button>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
function ping() {
  $.post("retrieve.php", {
    action: "ping",
    target: $("#ip").val()
  }, function(resp) {
    alert(resp);
  });
}
</script>
