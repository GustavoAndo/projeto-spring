function exibir(id){
	if(document.getElementById(id).style.display == 'block'){
		document.getElementById(id).style.display = 'none'
	} else {
		document.getElementById(id).style.display = 'block'
	}
}

function trocarTarefas(idAbaAtual, idContainerAtual, idAba, idContainer){
	document.getElementById(idContainerAtual).style.display = 'none'
	document.getElementById(idAbaAtual).style.backgroundColor = '#90ee90' 
	document.getElementById(idAbaAtual).style.color = '#000000' 
	document.getElementById(idContainer).style.display = 'block'
	document.getElementById(idAba).style.backgroundColor = '#008000'
	document.getElementById(idAba).style.color = '#ffffff' 
	
}