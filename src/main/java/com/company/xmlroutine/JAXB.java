package com.company.xmlroutine;

import com.company.pojo.User;

import javax.xml.bind.*;
import java.io.File;

public class JAXB {
    public static void main(String[] args) {
    }
    public void serialize(Object obj, String filename){
        try{
            File file=new File(filename);
            System.out.println(obj.getClass().getSimpleName());
            JAXBContext jaxbContext=JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbmarshaller=jaxbContext.createMarshaller();
            jaxbmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            jaxbmarshaller.marshal(obj,file);
            jaxbmarshaller.marshal(obj,System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Object unmarshal(Class c, File filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller um = context.createUnmarshaller();
        return um.unmarshal(filename);
    }


    public void deserialize() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance ("com.company.jaxb");
        Unmarshaller unmarshaller =jaxbContext.createUnmarshaller();
//        unmarshaller.setEventHandler(new UserValidationEventHandler());
//        JAXBElement<User> userElement= (JAXBElement<User>) unmarshaller.unmarshal(new File("user.xml"));
//        User userElement = (User)((JAXBElement)unmarshaller.unmarshal(new File("user.xml")));
        JAXBElement<User> userElement= (JAXBElement<User>) unmarshaller.unmarshal(new File("user.xml"));
        User user1 =(User)userElement.getValue();
    }

}
