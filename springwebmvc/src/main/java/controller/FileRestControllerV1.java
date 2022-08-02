package controller;

import facade.BookingFacade;
import model.Event;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class FileRestControllerV1 {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<byte[]> eventByTitle(
            @RequestParam String title,
            @RequestParam Integer size,
            @RequestParam Integer num)
            throws IOException {
        List<Event> eventsByTitle = bookingFacade.getEventsByTitle(title, size, num);
        System.out.println(eventsByTitle);
        return createPdf(eventsByTitle.toString());
    }

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<byte[]> eventByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam Integer size,
            @RequestParam Integer num)
            throws IOException {
        List<Event> eventsForDay = bookingFacade.getEventsForDay(date, size, num);
        System.out.println(eventsForDay);
        return createPdf(eventsForDay.toString());
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> createPdf(String string) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(30, page.getMediaBox().getHeight() - 30);
        contentStream.showText(string);
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(byteArrayOutputStream.toByteArray());
    }
}
