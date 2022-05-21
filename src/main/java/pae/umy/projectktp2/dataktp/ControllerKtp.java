/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pae.umy.projectktp2.dataktp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Controller
public class ControllerKtp {
    DataKtpJpaController ktpctrl = new DataKtpJpaController();
    List<DataKtp> data = new ArrayList<>();
    
     @RequestMapping("/home")
    public String createDummy(){
        return "ktpHome";
    }
    
     @PostMapping(value="/ktp_newdata", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
     @ResponseBody
       public String newDummy(@RequestParam("foto") MultipartFile file, HttpServletRequest req, String nama) throws ParseException, Exception{
        DataKtp dataktp = new DataKtp();

        
        String tgl = req.getParameter("tanggal");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
        byte[] img = file.getBytes();
        dataktp.setNama(nama);
        dataktp.setTanggal(date);
        dataktp.setFoto(Base64.getEncoder().encodeToString(img));
        
        ktpctrl.create(dataktp);
        return "/ktplist.html";
    }
       
       
       
//         @RequestMapping("/list")
//    public String getDataKtp(){
//        return "ktplist";
//    }
       
     @GetMapping("/ktpList")
	public String showExampleView(Model model)
	{
		List<DataKtp> dataKtp = ktpctrl.findDataKtpEntities();
		model.addAttribute("dataKtp", dataKtp);
		return "ktplist";
	}
       
       
     
    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") int id)
    {
    	
    	ktpctrl.destroy(id);
    	return "redirect:/listProducts.html";
    }
    
}
