package at.willhaben.workshop.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class ExchangeRefreshToken {

    public static void main(String [] args ) throws IOException, InterruptedException {
        String refreshToken ="eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.Uw5C9BfP3I-n0DTsK7uQpn6VrlRNrsGuFZ3DBKHKRgQp4J6BfYGFlhV5CDCV_XvFAVTq9MD0t3RYbc9RO1RhdZdnK9Bux5fsQ8EPH6wEVu_HaPoF8nAo8UqtKDEp3gXuHjtJPEUTj5S-gSmVkD5My55Ffwmu00xpDwhVR8NpGxEUnpSYnZHmR5f44dpDwdmG9JDvQUlzOhHX44G_hL9uzZBfc4wz8ihbNPk73Km9rkOUv6o8Ixjm08szNbpORkYKPLx0wss8yE7PsKxqkq9xGImQtmOl7t9ug-r2bPeB9UkcdpgwUELaYCcB4eoc3t4XMc2o22dSZcuR1ikmLDOMhQ.MH0kdsoXp1AVZQw5.P8-omtQCTLHN-hFq_FciSwzzZGQfJ7TW_rff5vyVi3K4JqDniH8EjnDJHA1bYAxe3GSOud6lzu_faR6qVPZpI_hZkJAhCAh2gKbyGJ5E00g90NgpTzt8a-u_-zosrMAXzdLkeBnNo4e3aPPr4MQQuBy3yKYIv_-P4BFwSKpGN9j7_ORkdEReyHyn6RVOq6vuHmfueeZ8Pn9NlcDHmPe2ekhyZc0DAVoiaZ0WTafNhVFEnkc1vM_wqvKoZPpK4DHwz0pmPokb9RmuLHngXp2PV9tLdh017EO9K8VYH6yRNwqvhzGe2gjqOLean3By7TZYzKk8j_RII-vK2QAxTrLCsVhugAIYu46hrS8j6hGfxubo359vrSF0Y3fPRdb_bVGY5tUiPlUfsOGf3vL2KU16iuaZBdP90qQRnMnnb_H_mXaex6JN6owHSMoabC27mA4UkhJ1HEtTLhxWCzUGfc49KFQYBUT99qbpAGQB4fvbvrsgiKzPe6RQnEYjOc09gYejFmrlxCjNhYSBZZii5iC7cpk70KILjUoM5ZUt7ZK1RnJaHd22Xj-T9GIVRVgpq2X6GDHKfM39SSSuc27YBeSguIAMqprmEJMPFI-lR9UBf2CuzkZZRF3M1WgJ-bb0PZ0HOp57vbhh_jqN2vnUcfd-VZWFeo4D1OQGt6hrVrt1qFSeIBLfbYj4OMZurbohoOqHb1nzPwHpeZ1XXuMbX8Z_mdnXhlve03Fi-mwHdUrYzCmICwcSBoLmpYGWtpms1GpXGQp8lg2T94mH5j4pB_aTExgv6Sz94Sc5cdIQbKg1G-bRAGkiSJCOynJ2tg6O8YL2hSG742UgH2iLQEaZPkYGMg7P5jfoq9Z7gM4u9E5yxtFolmpjHqh6IBHO8sLkMm8_VkgnfarkRJHsWsqNPr1UINyxUx1kXIYBTrSCEYf4d3_fSJ8W7HHiij0PE0yK2kbMdR4nmCybJDEiwRUpHl5RNkhegWmC-0LecoAhpAAHMIon7h-QO1R7CCfQFXLWM9PM4KYHIfVQf1wR55S-jfvHUr61jytv9PM-EerGhtOZMhpm9MOW5k3mKh8p1wcbQADdlu9pYOp8tRcBmmqrpJxw0gzuTOiuFzNJDkJbbVRK51-ke659d7pqWDTdn6lJ5k1v5nFG9-V7cKCLVe_uZpN6w4AKdUa-3_UZ4TkfNjRRDV3riIGhRsY54uk8a1qXJkzFEx_XACugsdyMrwrAvQRIXTq-98EIgj3q4i7RMXMBSU-iMkd_jyJHf77wYBJRY8Ovjcqus5AqIEeNiILqR6CdXIbGR4cGJVuPFgY12DTHaSi8Ev_xRGShzgu8hj3_CA9pvjqQl83iiuuX.OVbRhOYO5GlZgIrRlmbRMw";
        String exchange ="https://devjourfix-willhaben-test.auth.eu-central-1.amazoncognito.com/oauth2/token";
        String header = Base64.getEncoder().encodeToString("5vkkniseds4i1s4pst0uoe280u:1r1lkqea5tuh4scm7lkd42mcl32pgrbpkim5gvsp6bvgne7e55f7".getBytes());
        String body = "grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=5vkkniseds4i1s4pst0uoe280u";
        HttpRequest authorization = HttpRequest.newBuilder().uri(URI.create(exchange))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic "+header)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> send = HttpClient.newHttpClient().send(authorization, HttpResponse.BodyHandlers.ofString());
        String result = send.body();
    }

}
