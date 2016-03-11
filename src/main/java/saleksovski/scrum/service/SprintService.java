package saleksovski.scrum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saleksovski.scrum.model.Board;
import saleksovski.scrum.model.Sprint;
import saleksovski.scrum.repository.BoardRepository;
import saleksovski.scrum.repository.SprintRepository;

import java.util.Set;

/**
 * Created by stefan on 2/25/16.
 */
@Service
public class SprintService {

    private SprintRepository sprintRepository;
    private BoardRepository boardRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository, BoardRepository boardRepository) {
        this.sprintRepository = sprintRepository;
        this.boardRepository = boardRepository;
    }

    public Set<Sprint> findByBoardSlug(String slug) {
        Board board = boardRepository.findBySlug(slug);
        if (board != null) {
            return board.getSprints();
        }
        return null;
    }

    public Sprint findById(Long id) {
        return sprintRepository.findOne(id);
    }

    public Sprint createSprint(String slug, Sprint sprint) {
        Board board = boardRepository.findBySlug(slug);
        sprint.setBoard(board);
        return sprintRepository.save(sprint);
    }

}
