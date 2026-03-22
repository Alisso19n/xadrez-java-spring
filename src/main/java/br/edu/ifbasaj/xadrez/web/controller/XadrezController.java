package br.edu.ifbasaj.xadrez.web.controller;

import br.edu.ifbasaj.xadrez.application.dto.MovimentoRequestDTO;
import br.edu.ifbasaj.xadrez.application.dto.MovimentoResponseDTO;
import br.edu.ifbasaj.xadrez.application.dto.TabuleiroDTO;
import br.edu.ifbasaj.xadrez.application.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class XadrezController {

    private final PartidaService partidaService;

    public XadrezController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping("/tabuleiro")
    public TabuleiroDTO getTabuleiro() {
        return partidaService.obterTabuleiro();
    }

    @PostMapping("/movimento")
    public ResponseEntity<MovimentoResponseDTO> mover(@RequestBody MovimentoRequestDTO dto) {
        MovimentoResponseDTO resp = partidaService.mover(
                dto.getOrigemLinha(),
                dto.getOrigemColuna(),
                dto.getDestinoLinha(),
                dto.getDestinoColuna(),
                dto.getPromocaoOpcional()
        );

        return resp.isSucesso()
                ? ResponseEntity.ok(resp)
                : ResponseEntity.badRequest().body(resp);
    }

    @PostMapping("/desfazer")
    public MovimentoResponseDTO desfazer() {
        return partidaService.desfazer();
    }

    @PostMapping("/reiniciar")
    public TabuleiroDTO reiniciar() {
        partidaService.reiniciar();
        return partidaService.obterTabuleiro();
    }
}
