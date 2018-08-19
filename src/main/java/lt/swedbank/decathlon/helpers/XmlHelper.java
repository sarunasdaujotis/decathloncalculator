package lt.swedbank.decathlon.helpers;

import lt.swedbank.decathlon.model.Competition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlHelper {
    public static void generateDocument(Competition competition, String outputFileName) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Competition.class);
        Marshaller marshaller = context.createMarshaller();
        FileWriter fw = new FileWriter(new File(outputFileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type='text/xsl' href='style.xsl' ?>\n");
        marshaller.marshal(competition, fw);
        fw.close();
    }
}
