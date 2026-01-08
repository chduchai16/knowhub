package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.exceptions.post.tag.TagNotFoundException;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import com.spring.knowhub.infrastructure.entities.post.TagEntity;
import com.spring.knowhub.infrastructure.exceptions.post.tag.TagMapperException;
import com.spring.knowhub.infrastructure.exceptions.post.tag.TagRepositoryException;
import com.spring.knowhub.infrastructure.mappers.post.TagMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.post.JpaTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TagRepositoryImpl implements TagRepository {

    private final JpaTagRepository jpaTagRepository ;
    private final TagMapper tagMapper ;

    @Override
    public Optional<Tag> findById(Long id) {
        log.info("Tìm kiếm Tag với ID: {}", id);
        try {
            Optional<Tag> tagOptional = jpaTagRepository.findById(id).map(tagMapper::fromEntityToDomain);
            if (tagOptional.isPresent()) {
                log.info("Tìm thấy Tag với ID: {}", id);
            } else {
                log.info("Không tìm thấy Tag với ID: {}", id);
            }
            return tagOptional;
        } catch (TagMapperException ex) {
            log.error("Lỗi mapping khi tìm Tag với ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm Tag với ID: {}", id, ex);
            throw TagRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public List<Tag> findByIds(List<Long> ids) {
        log.info("Tìm kiếm Tags với IDs: {}", ids);
        try {
            List<TagEntity> tagEntities = jpaTagRepository.findAllById(ids);
            List<Tag> tags = tagEntities.stream()
                    .map(tagMapper::fromEntityToDomain)
                    .toList();
            log.info("Tìm thấy {} Tags với IDs", tags.size());
            return tags ;
        } catch(TagMapperException ex) {
            log.error("Lỗi mapping khi tìm Tags với IDs: {}", ids, ex);
            throw ex ;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm Tags với IDs: {}", ids, ex);
            throw TagRepositoryException.findFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Page<Tag> findTagsPaged(Pageable pageable) {
        log.info("Tìm kiếm Tag phân trang: trang số {}, kích thước trang {}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Tag> tagPage = jpaTagRepository.findAll(pageable).map(tagMapper::fromEntityToDomain);
            log.info("Tìm thấy {} Tag phân trang", tagPage.getNumberOfElements());
            return tagPage ;
        } catch(TagMapperException ex) {
            log.error("Lỗi mapping khi tìm Tag phân trang", ex);
            throw ex ;
        }catch (Exception ex) {
            log.error("Lỗi khi tìm Tag phân trang", ex);
            throw TagRepositoryException.findFailed(ex.getMessage()) ;
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa Tag với ID: {}", id);
        try {
            jpaTagRepository.deleteById(id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa Tag với ID: {}", id, ex);
            throw TagRepositoryException.deleteFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Optional<Tag> save(Tag tag) {
        log.info("Lưu Tag: {}", tag);
        try {
            TagEntity tagEntity = tagMapper.fromDomainToEntity(tag) ;
            TagEntity savedEntity = jpaTagRepository.save(tagEntity) ;
            Tag savedTag = tagMapper.fromEntityToDomain(savedEntity) ;
            log.info("Lưu Tag thành công: {}", savedTag);
            return Optional.of(savedTag) ;
        } catch(TagMapperException ex) {
            log.error("Lỗi mapping khi lưu Tag: {}", tag, ex);
            throw ex ;
        }catch (Exception ex) {
            log.error("Lỗi khi lưu Tag: {}", tag, ex);
            throw TagRepositoryException.saveFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Optional<Tag> findByName(String name) {
        log.info("Tìm kiếm Tag với tên: {}", name);
        try {
            Optional<TagEntity> tagEntityOptional = jpaTagRepository.findByName(name);
            Optional<Tag> tagOptional = tagEntityOptional.map(tagMapper::fromEntityToDomain);
            if (tagOptional.isPresent()) {
                log.info("Tìm thấy Tag với tên: {}", name);
            } else {
                log.info("Không tìm thấy Tag với tên: {}", name);
            }
            return tagOptional;
        } catch (TagMapperException ex) {
            log.error("Lỗi mapping khi tìm Tag với tên: {}", name, ex);
            throw ex ;
        }catch (Exception ex) {
            log.error("Lỗi khi tìm Tag với tên: {}", name, ex);
            throw TagRepositoryException.findFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Boolean existsByName(String name) {
        log.info("Kiểm tra tồn tại Tag với tên: {}", name);
        try {
            Boolean exists = jpaTagRepository.existsByName(name);
            log.info("Tag với tên '{}' tồn tại: {}", name, exists);
            return exists ;
        } catch (Exception ex) {
            log.error("Lỗi khi kiểm tra tồn tại Tag với tên: {}", name, ex);
            throw TagRepositoryException.findFailed(ex.getMessage()) ;
        }
    }
}
