function addRow() {
	$("#leaderboard-table tbody").append(
		"<tr>" + "<th th:text=\"${rank2}\"/>" +
		"<td th:text=\"${name2}\" />" +
		"<td th:text=\"${achievement2}\" />" +
		"<td th:text=\"${completion}\" />" +
		"<td th:text=\"${country2}\" />") + "</tr>";
};