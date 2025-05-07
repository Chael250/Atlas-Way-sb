    package com.chael.Atlas_Way_sb.controllers;

    import com.chael.Atlas_Way_sb.dtos.AttractionDto;
    import com.chael.Atlas_Way_sb.entities.Attraction;
    import com.chael.Atlas_Way_sb.mappers.AttractionDtoToAttraction;
    import com.chael.Atlas_Way_sb.repositories.AttractionRepository;
    import com.chael.Atlas_Way_sb.services.AttractionService;
    import com.chael.Atlas_Way_sb.util.Constants;
    import org.springframework.data.domain.Page;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.w3c.dom.Attr;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/atlas-way/attractions")
    public class AttractionController {
        private final AttractionService attractionService;

        public AttractionController(AttractionService attractionService) {
            this.attractionService = attractionService;
        }

        @GetMapping()
        public List<Attraction> getAllAttractions() {
            return attractionService.findAll();
        }

        @GetMapping("/{id}")
        public Attraction getAttractionById(@PathVariable Long id) {
            return attractionService.findById(id);
        }

        @PostMapping()
        public Attraction createAttraction(@RequestBody AttractionDto attractiondto) {
            return attractionService.save(attractiondto);
        }

        @DeleteMapping("/{id}")
        public Attraction deleteAttraction(@PathVariable Long id) {
            Attraction attraction = attractionService.findById(id);
            attractionService.deleteById(id);
            return attraction;
        }

        @PutMapping("/update/{attractionId}")
        public ResponseEntity<?> updateAttraction(@PathVariable Long attractionId, @RequestBody AttractionDto attractiondto) {
            boolean result = attractionService.updateProduct(attractionId, attractiondto);
            if (result) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }

        @GetMapping("/page")
        public Page<Attraction> getPageAttractions(
                @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                @RequestParam(value = "column", defaultValue = "name") String column,
                @RequestParam(value = "direction", defaultValue = "asc") String direction) {

            return attractionService.findPageAttraction(page, size, column, direction);
        }
    }
