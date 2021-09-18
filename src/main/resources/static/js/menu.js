function menu() {
    document.querySelector("div#menukey")
    if(document.querySelector("ul#list").style.display == 'flex'){
        document.querySelector("ul#list").style.display = 'none'
    }else{
        document.querySelector("ul#list").style.display = 'flex' 
    }
}