function addRow() {
	$("#leaderboard-table tbody").append(
		"<tr>" + "<th th:text='${rank1}'/>" +
		"<td>...</td>" +
		"<td>...</td>" +
		"<td>...</td>" +
		"<td>...</td>");
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