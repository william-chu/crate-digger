
import java.math.BigDecimal;
import java.util.*;


import dao.Sql2oReleaseDao;
import dao.Sql2oArtistDao;
import dao.Sql2oNoteDao;
import dao.Sql2oTrackDao;
import models.Artist;
import models.Note;
import models.Release;
import models.Track;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/cratedigger";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        Sql2oReleaseDao releaseDao = new Sql2oReleaseDao(sql2o);
        Sql2oArtistDao artistDao = new Sql2oArtistDao(sql2o);
        Sql2oNoteDao noteDao = new Sql2oNoteDao(sql2o);
        Sql2oTrackDao trackDao = new Sql2oTrackDao(sql2o);

        //get: show recent releases
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            List<Release> recentReleases = releaseDao.getRecent();
            model.put("releases", recentReleases);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : recentReleases) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show releases in collection
        get("/releases", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> allReleases;
            if(query != null) {
                allReleases = releaseDao.search(query);
            } else {
                allReleases = releaseDao.getAll();
            }
            model.put("releases", allReleases);

            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : allReleases) {
               List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
               releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show singles in collection
        get("/releases/singles", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> allReleases;
            if(query != null) {
                allReleases = releaseDao.search(query);
            } else {
            allReleases = releaseDao.getAllSingles(true);
            }
            model.put("releases", allReleases);

            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : allReleases) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show EPs in collection
        get("/releases/EPs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> allReleases;
            if(query != null) {
                allReleases = releaseDao.search(query);
            } else {
            allReleases = releaseDao.getAllEps(true);
            }
            model.put("releases", allReleases);

            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : allReleases) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show LPs in collection
        get("/releases/LPs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> allReleases;
            if(query != null) {
                allReleases = releaseDao.search(query);
            } else {
            allReleases = releaseDao.getAllLps(true);
            }
            model.put("releases", allReleases);

            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : allReleases) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show 78s in collection
        get("/releases/78rpms", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> allReleases;
            if(query != null) {
                allReleases = releaseDao.search(query);
            } else {
            allReleases = releaseDao.getAllSeventies(true);
            }
            model.put("releases", allReleases);

            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : allReleases) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show releases in wishlist
        get("/releases/wishlist", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> wishlist;
            if(query != null) {
                wishlist = releaseDao.search(query);
            } else {
                wishlist = releaseDao.getWishlist();
            }
            model.put("wishlist", wishlist);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : wishlist) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show singles in wishlist
        get("/releases/wishlist/singles", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> wishlist;
            if(query != null) {
                wishlist = releaseDao.search(query);
            } else {
                wishlist = releaseDao.getAllSingles(false);
            }
            model.put("wishlist", wishlist);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : wishlist) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show EPs in wishlist
        get("/releases/wishlist/EPs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
//            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
//            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> wishlist;
            if(query != null) {
                wishlist = releaseDao.search(query);
            } else {
                wishlist = releaseDao.getAllEps(false);
            }
            model.put("wishlist", wishlist);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : wishlist) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show LPs in wishlist
        get("/releases/wishlist/LPs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> wishlist;
            if(query != null) {
                wishlist = releaseDao.search(query);
            } else {
                wishlist = releaseDao.getAllLps(false);
            }
            model.put("wishlist", wishlist);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : wishlist) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show 78s in wishlist
        get("/releases/wishlist/78rpms", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Release> wishlist;
            if(query != null) {
                wishlist = releaseDao.search(query);
            } else {
                wishlist = releaseDao.getAllSeventies(false);
            }
            model.put("wishlist", wishlist);
            Map<Release, List<Artist>> releasesWithArtists = new LinkedHashMap<>();
            for (Release release : wishlist) {
                List<Artist> releaseArtists = releaseDao.getAllArtistsByReleaseId(release.getId());
                releasesWithArtists.put(release, releaseArtists);
            }
            model.put("releasesWithArtists", releasesWithArtists);
            return new ModelAndView(model, "releases.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all artists
        get("/artists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            String query = req.queryParams("query");
            List<Artist> allArtists;
            if(query != null) {
                allArtists = artistDao.search(query);
            } else {
            allArtists = artistDao.getAll();
            }
            model.put("artists", allArtists);
            return new ModelAndView(model, "artists.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show form for adding artist to release
        get("/releases/:id/artists/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            List<Artist> allArtists = artistDao.getAll();
            model.put("artists", allArtists);
            int releaseId = Integer.parseInt(req.params("id"));
            Release release = releaseDao.findById(releaseId);
            model.put("release", release);
            model.put("addArtist", true);
            return new ModelAndView(model, "release-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process adding artist to release form
        post("releases/:id/artists", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Artist> allArtists = artistDao.getAll();
            List<String> allArtistNames = new ArrayList<>();
            for (Artist artist:allArtists) {
                allArtistNames.add(artist.getName());
            }
            String artistName = req.queryParams("artistName");
            String artistImageUrl = req.queryParams("artistImageUrl");
            Artist newArtist = new Artist(artistName, artistImageUrl);
            if (!allArtistNames.contains(newArtist.getName())) {
                artistDao.add(newArtist);
            }
            Artist artist = artistDao.findByName(artistName);
            int releaseId = Integer.parseInt(req.params("id"));
            Release release = releaseDao.findById(releaseId);
            artistDao.addArtistToRelease(artist, release);
            res.redirect("/releases/" + release.getId());
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show details and releases of single artist
        get("/artists/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            int idOfArtistToFind = Integer.parseInt(req.params("id"));
            Artist artist = artistDao.findById(idOfArtistToFind);
            List<Release> releases = artistDao.getAllReleasesByArtistId(idOfArtistToFind);
            List<Release> wishlist = artistDao.getWishlistByArtistId(idOfArtistToFind);
            model.put("artist", artist);
            model.put("releases", releases);
            model.put("wishlist", wishlist);
            String hasReleases = "";
            if (releases.size() == 0 && wishlist.size() == 0){
                hasReleases = "Hell Yeah!";
            }
            model.put("hasReleases", hasReleases);
            return new ModelAndView(model, "artist-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get:show new artist/release form
        get("/releases/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            List<Artist> allArtists = artistDao.getAll();
            model.put("artists", allArtists);
            return new ModelAndView(model, "release-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new artist/release form
        post("/releases", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            List<Artist> allArtists = artistDao.getAll();
            List<String> allArtistNames = new ArrayList<>();
            for (Artist artist:allArtists) {
                allArtistNames.add(artist.getName());
            }
            String artistName = req.queryParams("artistName");
            String artistImageUrl = req.queryParams("artistImageUrl");
            Artist newArtist = new Artist(artistName, artistImageUrl);
            if (!allArtistNames.contains(newArtist.getName())) {
                artistDao.add(newArtist);
            }
            Artist artist = artistDao.findByName(artistName);
            String title = req.queryParams("title");
            String label = req.queryParams("label");
            String labelNumber = req.queryParams("labelNumber");
            int mediaCondition = Integer.parseInt(req.queryParams("mediaCondition"));
            String sleeveType = req.queryParams("sleeveType");
            int sleeveCondition = Integer.parseInt(req.queryParams("sleeveCondition"));
            String seller = req.queryParams("seller");
            String mediaType = req.queryParams("mediaType");
            int price = (int)(Double.parseDouble(req.queryParams("price"))*100);
            String datePurchased = req.queryParams("datePurchased");
            boolean isInCollection = Boolean.parseBoolean(req.queryParams("isInCollection"));
            String releaseImageUrl = req.queryParams("releaseImageUrl");
            Release newRelease = new Release(title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, releaseImageUrl);
            releaseDao.add(newRelease);
            Release release = releaseDao.findById(newRelease.getId());
            artistDao.addArtistToRelease(artist, release);
            String content = req.queryParams("content");
            int releaseId = release.getId();
            Note newNote = new Note(content, releaseId);
            if (!newNote.getContent().equals("")) {
                noteDao.add(newNote);
            }
            res.redirect("/releases");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show details of specific release
        get("/releases/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            int idOfReleaseToFind = Integer.parseInt(req.params("id"));
            Release release = releaseDao.findById(idOfReleaseToFind);
            List<Artist> artists = releaseDao.getAllArtistsByReleaseId(idOfReleaseToFind);
            List<Note> notes = noteDao.getAllByReleaseId(idOfReleaseToFind);
            List<Track> tracks = trackDao.getAllByReleaseId(idOfReleaseToFind);
            String sleeveCondition = condition(release.getSleeveCondition());
            String mediaCondition = condition(release.getMediaCondition());
            model.put("notes", notes);
            model.put("artists", artists);
            model.put("release", release);
            model.put("tracks", tracks);
            model.put("sleeveCondition", sleeveCondition);
            model.put("mediaCondition", mediaCondition);
            BigDecimal payment = new BigDecimal(release.getPrice()).movePointLeft(2);
            model.put("dollarPrice", payment);
            return new ModelAndView(model, "release-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: update form for specific artist
        get("/artists/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            int idOfArtistToUpdate = Integer.parseInt(req.params("id"));
            Artist editArtist = artistDao.findById(idOfArtistToUpdate);
            model.put("editArtist", editArtist);
            List<Artist> artists = artistDao.getAll();
            model.put("artists", artists);
            return new ModelAndView(model, "release-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process update on specific artist
        post("/artists/:id/update", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            int idOfArtistToFind = Integer.parseInt(req.params("id"));
            String name = req.queryParams("artistName");
            String imageUrl = req.queryParams("artistImageUrl");
            Artist newArtist = new Artist(name, imageUrl);
            artistDao.update(idOfArtistToFind, name,imageUrl);
            res.redirect("/artists/" + idOfArtistToFind);
            return null;
        }, new HandlebarsTemplateEngine());

        //get: update form for specific release
        get("/releases/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            int idOfReleaseToUpdate = Integer.parseInt(req.params("id"));
            Release editRelease = releaseDao.findById(idOfReleaseToUpdate);
            model.put("editRelease", editRelease);
            return new ModelAndView(model, "release-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process update on specific release
        post("/releases/:id/update", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            int idOfReleaseToFind = Integer.parseInt(req.params("id"));
            String title = req.queryParams("title");
            String label = req.queryParams("label");
            String labelNumber = req.queryParams("labelNumber");
            int mediaCondition = Integer.parseInt(req.queryParams("mediaCondition"));
            String sleeveType = req.queryParams("sleeveType");
            int sleeveCondition = Integer.parseInt(req.queryParams("sleeveCondition"));
            String seller = req.queryParams("seller");
            String mediaType = req.queryParams("mediaType");
            int price = Integer.parseInt(req.queryParams("price"));
            String datePurchased = req.queryParams("datePurchased");
            boolean isInCollection = Boolean.parseBoolean(req.queryParams("isInCollection"));
            String imageUrl = req.queryParams("releaseImageUrl");
            Release newRelease = new Release(title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl);
            releaseDao.update(idOfReleaseToFind, title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl);
            res.redirect("/releases/" + idOfReleaseToFind);
            return null;
        }, new HandlebarsTemplateEngine());

        //get: get to-collection form
        get("/releases/:id/to-collection", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int releasesSize = releaseDao.getAll().size();
            int artistSize =  artistDao.getAll().size();
            int singlesSize = releaseDao.getAllSingles(true).size();
            int seventiesSize = releaseDao.getAllSeventies(true).size();
            int epsSize = releaseDao.getAllEps(true).size();
            int lpsSize = releaseDao.getAllLps(true).size();
            BigDecimal total = new BigDecimal(releaseDao.getTotalValue()).movePointLeft(2);
            model.put("releasesSize", releasesSize);
            model.put("artistsSize", artistSize);
            model.put("seventiesSize", seventiesSize);
            model.put("singlesSize", singlesSize);
            model.put("epsSize", epsSize);
            model.put("lpsSize", lpsSize);
            model.put("total", total);
            int idOfReleaseToAdd = Integer.parseInt(req.params("id"));
            Release addRelease = releaseDao.findById(idOfReleaseToAdd);
            model.put("addRelease", addRelease);
            return new ModelAndView(model, "release-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process update on specific release
        post("/releases/:id/to-collection", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            int idOfReleaseToFind = Integer.parseInt(req.params("id"));
            String title = req.queryParams("title");
            String label = req.queryParams("label");
            String labelNumber = req.queryParams("labelNumber");
            int mediaCondition = Integer.parseInt(req.queryParams("mediaCondition"));
            String sleeveType = req.queryParams("sleeveType");
            int sleeveCondition = Integer.parseInt(req.queryParams("sleeveCondition"));
            String seller = req.queryParams("seller");
            String mediaType = req.queryParams("mediaType");
            int price = Integer.parseInt(req.queryParams("price"));
            String datePurchased = req.queryParams("datePurchased");
            boolean isInCollection = Boolean.parseBoolean(req.queryParams("isInCollection"));
            String imageUrl = req.queryParams("releaseImageUrl");
            Release newRelease = new Release(title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl);
            releaseDao.update(idOfReleaseToFind, title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl);
            res.redirect("/releases/" + idOfReleaseToFind);
            return null;
        }, new HandlebarsTemplateEngine());

        // get: delete specific artist
        get("/artists/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfArtistToDelete = Integer.parseInt(req.params("id"));
            artistDao.deleteById(idOfArtistToDelete);
            res.redirect("/artists");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete specific release
        get("/releases/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfReleaseToDelete = Integer.parseInt(req.params("id"));
            releaseDao.deleteById(idOfReleaseToDelete);
            res.redirect("/releases");
            return null;
        }, new HandlebarsTemplateEngine());

        //post: process form to add new note
        post("/releases/:id/notes", (req, res) -> {
            String content = req.queryParams("content");
            int releaseId = Integer.parseInt(req.params("id"));
            Note newNote = new Note(content, releaseId);
            noteDao.add(newNote);
            res.redirect("/releases/" + releaseId);
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete specific note
        get("/notes/:id/delete", (req, res) -> {
            int idOfNoteToDelete = Integer.parseInt(req.params("id"));
            Note noteToDelete = noteDao.findById(idOfNoteToDelete);
            int releaseId = noteToDelete.getReleaseId();
            noteDao.deleteById(idOfNoteToDelete);
            res.redirect("/releases/" + releaseId);
            return null;
        }, new HandlebarsTemplateEngine());

        //post: process form to add new track
        post("/releases/:id/tracks", (req, res) -> {
            String title = req.queryParams("title");
            int releaseId = Integer.parseInt(req.params("id"));
            Track newTrack = new Track(title, releaseId);
            trackDao.add(newTrack);
            res.redirect("/releases/" + releaseId);
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete specific track
        get("/tracks/:id/delete", (req, res) -> {
            int idOfTrackToDelete = Integer.parseInt(req.params("id"));
            Track trackToDelete = trackDao.findById(idOfTrackToDelete);
            int releaseId = trackToDelete.getReleaseId();
            trackDao.deleteById(idOfTrackToDelete);
            res.redirect("/releases/" + releaseId);
            return null;
        }, new HandlebarsTemplateEngine());

    }

    public static String condition(int conditionAsNumber) {
        String result = "";
        if (conditionAsNumber == 1) {
            result = "P";
        } else if (conditionAsNumber == 2) {
            result = "G";
        } else if (conditionAsNumber == 3) {
            result = "VG-";
        } else if (conditionAsNumber == 4) {
            result = "VG";
        } else if (conditionAsNumber == 5) {
            result = "VG+";
        } else if(conditionAsNumber == 6) {
            result = "NM";
        }
        return result;
    }


}