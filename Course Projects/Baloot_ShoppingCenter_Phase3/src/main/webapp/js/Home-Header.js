$(document).ready(function(){
    $(".default_option").click(function(){
        $(".dropdown ul").addClass("active");
        $(".default_option i").addClass("active");
        $(".option-list i").addClass("active");
    });

    $(".dropdown ul li").click(function(){
        $(".dropdown ul").removeClass("active");
        $(".default_option i").removeClass("active");
        $(".option-list i").removeClass("active");
        var text = $(this).text();
        $(".default_option").text(text);
    });
});

function HomeHeader() {

    function search_commodity_event_handler(){
        window.location.href = "commodities?" + document.getElementById("search_subject").textContent + "=" + document.getElementById("search_input").value;
    };

    return (
        <div className="home-header">
            <img src="photo/Baloot Logo.png" />
            <div className="btn-group">
                <a href={"authentication"}><button>Register</button></a>
                <a href={"authentication"}><button>Login</button></a>
            </div>
            <div className="wrapper">
                <div className="search_box">
                    <div className="dropdown">
                        <div className="default_option" id="search_subject">name
                            <i className="bi bi-chevron-down"></i>
                        </div>
                        <div className="option-list">
                            <i className="bi bi-chevron-up"></i>
                        </div>
                        <ul>
                            <li>name</li>
                            <li>category</li>
                        </ul>
                    </div>
                    <div className="search_field">
                        <input type="text" className="input" id="search_input" placeholder="search your product ..." />
                        <i onClick={search_commodity_event_handler} className="bi bi-search"></i>
                    </div>
                </div>
            </div>
        </div>
    );
}