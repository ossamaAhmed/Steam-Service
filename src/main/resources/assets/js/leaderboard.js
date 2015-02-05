function addRow() {
	$("#leaderboard-table tbody").append(
		"<tr>" + "<th th:text='${rank2}'/>" +
		"<td th:text='${name2}' />" +
		"<td th:text='${achievement2}' />" +
		"<td th:text='${time2}' />" +
		"<td th:text='${country2}' />");
};

// function save() {
// 	var par = $(this).parent().parent();

// 	var trRank = par.children("tr:nth-child(0)");
// 	var tdName = par.children("td:nth-child(1)");
//  	var tdAchievements = par.children("td:nth-child(2)"); 
//  	var tdTimePlayed = par.children("td:nth-child(3)"); 
//  	var tdCountry = par.children("td:nth-child(4)"); 
//  	tdName.html(tdName.children("input[type=text]").val());
//   	tdAchievements.html(tdAchievements.children("input[type=text]").val()); 
//   	tdTimePlayed.html(tdTimePlayed.children("input[type=text]").val());
//   	tdTimePlayed.html(tdTimePlayed.children("input[type=text]").val());  	
//   	tdButtons.html("<img src='images/delete.png' class='btnDelete'/><img src='images/pencil.png' class='btnEdit'/>");
//    $(".btnEdit").bind("click", Edit); $(".btnDelete").bind("click", Delete); }; 
// }