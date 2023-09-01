package org.example;

import io.javalin.Javalin;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Shopping_WebSite site = new Shopping_WebSite();

        site.get_users_from_external_server("http://5.253.25.110:5000/api/users");
        site.get_providers_from_external_server("http://5.253.25.110:5000/api/providers");
        site.get_commodities_from_external_server("http://5.253.25.110:5000/api/commodities");
        site.get_users_comments_from_external_server("http://5.253.25.110:5000/api/comments");

        var web_server = Javalin.create();

        web_server.get("/users/{}", ctx->ctx.html(site.show_user_page_handler(ctx.pathParam("{}"))));
        web_server.get("/purchase/{}", ctx->ctx.html(site.purchase_items_in_buy_list_page_handler(ctx.pathParam("{}"))));
        web_server.get("/addCredit/{username}/{}", ctx->ctx.html(site.add_credit_page_handler(ctx.path())));
        web_server.get("/addToBuyList/{username}/{}", ctx->ctx.html(site.add_to_buy_list_page_handler(ctx.path())));
        web_server.get("/removeFromBuyList/{username}/{}", ctx->ctx.html(site.remove_from_buy_list_page_handler(ctx.path())));
        web_server.get("/voteComment/{username}/{comment_id}/{}", ctx->ctx.html(site.rate_comment_page_handler(ctx.path())));
        web_server.get("/rateCommodity/{username}/{commodity_id}/{}", ctx->ctx.html(site.rate_commodity_page_handler(ctx.path())));
        web_server.get("/redirect/addCredit/{username}/", ctx->ctx.html(site.redirect_to_addCredit_page(ctx.fullUrl())));
        web_server.get("/redirect/voteComment/{commodity_id}/", ctx->ctx.html(site.redirect_to_voteComment_page(ctx.fullUrl())));
        web_server.get("/redirect/addToBuyList/{commodity_id}/", ctx->ctx.html(site.redirect_to_addToBuyList_page(ctx.fullUrl())));
        web_server.get("/redirect/rateCommodity/{commodity_id}/", ctx->ctx.html(site.redirect_to_rateCommodity_page(ctx.fullUrl())));
        web_server.get("/redirect/removeFromBuyList/{commodity_id}/", ctx->ctx.html(site.redirect_to_removeFromBuyList_page(ctx.fullUrl())));

        web_server.get("/providers/{}", ctx->ctx.html(site.show_provider_page_handler(ctx.pathParam("{}"))));

        web_server.get("/commodities", ctx->ctx.html(site.show_all_commodities_page_handler()));
        web_server.get("/commodities/{}", ctx->ctx.html(site.show_commodity_page_handler(ctx.pathParam("{}"))));
        web_server.get("/commodities/search/{}", ctx->ctx.html(site.show_commodity_by_category_page_handler(ctx.pathParam("{}"))));
        web_server.get("/commodities/search/{start_price}/{}", ctx->ctx.html(site.show_commodity_by_range_of_price_page_handler(ctx.path())));

        web_server.start(8080);
        while(true)
        {

        }
    }
}