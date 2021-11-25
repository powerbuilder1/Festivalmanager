package festivalmanager.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        Assert.notNull(storageService, "storageService must not be null");
        this.storageService = storageService;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    /**
     * Upload route for location image
     * 
     * @return redirect to /location
     */
    @PostMapping("/location/{id}/edit/image")
    String editImage(@RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes,
            @PathVariable long id) {

        storageService.store(file, "location_" + id);

        return "redirect:/locations";
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<Resource> serveImage(@PathVariable String name) {
        Resource file = storageService.loadAsResource(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}