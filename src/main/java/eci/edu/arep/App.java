package eci.edu.arep;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/API", (req, res) -> callAPIgATEWAY(req));
        get("/", (req, res) -> loadHome());

    }

    private static Object loadHome() throws IOException {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<title>Calculator</title>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "\n" +
                "\t<script>\n" +
                "\tasync function clicked() {\n" +
                "\t  try {\n" +
                "\t\tconst response = await axios.get(\"API?value=\"+document.getElementById('number1').value);\n" +
                "\t\talert(\"the answer is: \"+response.data);\n" +
                "\t  } catch (error) {\n" +
                "\t\tconsole.error(error);\n" +
                "\t  }\n" +
                "\t}\n" +
                "\n" +
                "\t</script>\n" +
                "\t<script src=\"https://unpkg.com/axios/dist/axios.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"bg-contact2\" style=\"background-image: url('src/main/resources/public/welcomePage/images/bg-01.jpg');\">\n" +
                "\t<div class=\"container-contact2\">\n" +
                "\t\t<div class=\"wrap-contact2\">\n" +
                "\t\t\t<form class=\"contact2-form validate-form\" onsubmit=\"event.preventDefault()\">\n" +
                "\t\t\t\t\t<span class=\"contact2-form-title\">\n" +
                "\t\t\t\t\t\tThis is a calculator that add two numbers\n" +
                "\t\t\t\t\t</span>\n" +
                "\n" +
                "\t\t\t\t<div class=\"wrap-input2 validate-input\" data-validate=\"Number 1 is Required\">\n" +
                "\t\t\t\t\t<input class=\"input2\" id=\"number1\" name=\"number1\" type=\"text\">\n" +
                "\t\t\t\t\t<span class=\"focus-input2\" data-placeholder=\"Number 1\"></span>\n" +
                "\t\t\t\t</div>\n" +
                "\n" +
                "\n" +
                "\t\t\t\t<div class=\"container-contact2-form-btn\">\n" +
                "\t\t\t\t\t<div class=\"wrap-contact2-form-btn\">\n" +
                "\t\t\t\t\t\t<div class=\"contact2-form-bgbtn\"></div>\n" +
                "\t\t\t\t\t\t<button class=\"contact2-form-btn\" id=\"addBtn\" onclick=\"clicked()\">\n" +
                "\t\t\t\t\t\t\tAdd numbers\n" +
                "\t\t\t\t\t\t</button>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</form>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<!--===============================================================================================-->\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String callAPIgATEWAY(Request req) throws MalformedURLException {
        URL url = new URL("https://3eh7ctizv3.execute-api.us-east-1.amazonaws.com/Beta?value="+req.queryParams("value"));

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            String inputLine = null;
            inputLine = reader.readLine();
            return inputLine;
        } catch (IOException x) {
            System.err.println(x);
            return "404";
        }
    }

}