package com.spring.knowhub.infrastructure.repositories.impls.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import com.spring.knowhub.infrastructure.exceptions.media.MediaMapperException;
import com.spring.knowhub.infrastructure.exceptions.media.MediaRepositoryException;
import com.spring.knowhub.infrastructure.mappers.media.MediaMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.media.JpaMediaRepository;
import com.spring.knowhub.infrastructure.repositories.specifications.media.MediaJpaSpecificationAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MediaRepositoryImpl implements MediaRepository {

    private final JpaMediaRepository jpaMediaRepository;
    private final MediaMapper mediaMapper;

    @Override
    public Media save(Media media) {
        log.info("Bắt đầu lưu Media: {}", media);
        try {
            MediaEntity entity = mediaMapper.fromDomainToEntity(media);
            MediaEntity savedEntity = jpaMediaRepository.save(entity);
            Media savedMedia = mediaMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu Media thành công với ID: {}", savedMedia.getId());
            return savedMedia;
        } catch (DataIntegrityViolationException ex) {
            log.error("Vi phạm ràng buộc dữ liệu khi lưu Media: {}", media, ex);
            throw ex;
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi lưu Media: {}", media, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu Media: {}", media, ex);
            throw MediaRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public List<Media> saveAll(List<Media> mediaList) {
        log.info("Bắt đầu lưu danh sách Media: Số lượng = {}", mediaList.size());
        try {
            List<MediaEntity> entityList = mediaList.stream().map(mediaMapper::fromDomainToEntity).toList();
            List<MediaEntity> savedEntityList = jpaMediaRepository.saveAll(entityList);
            List<Media> savedMediaList = savedEntityList.stream().map(mediaMapper::fromEntityToDomain).toList();
            log.info("Lưu danh sách Media thành công: Số lượng = {}", savedMediaList.size());
            return savedMediaList;
        } catch (DataIntegrityViolationException ex) {
            log.error("Vi phạm ràng buộc dữ liệu khi lưu danh sách Media", ex);
            throw ex;
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi lưu danh sách Media", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu danh sách Media", ex);
            throw MediaRepositoryException.saveAllFailed(ex.getMessage());
        }
    }

    @Override
    public List<Media> findAllById(List<Long> ids) {
        log.info("Tìm kiếm danh sách Media theo IDs: {}", ids);
        try {
            List<MediaEntity> entities = jpaMediaRepository.findAllById(ids);
            List<Media> mediaList = entities.stream().map(mediaMapper::fromEntityToDomain).toList();
            log.info("Tìm kiếm danh sách Media thành công theo IDs: {}", ids);
            return mediaList;
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi tìm kiếm danh sách Media theo IDs: {}", ids, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm kiếm danh sách Media theo IDs: {}", ids, ex);
            throw MediaRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<Media> findPagedMedia(Pageable pageable) {
        log.info("Bắt đầu tìm kiếm trang Media: page = {}, size = {}", pageable.getPageNumber(),
                pageable.getPageSize());
        try {
            Page<MediaEntity> entityPage = jpaMediaRepository.findAll(pageable);
            Page<Media> mediaPage = entityPage.map(mediaMapper::fromEntityToDomain);
            log.info("Tìm kiếm trang Media thành công: page = {}, size = {}", pageable.getPageNumber(),
                    pageable.getPageSize());
            return mediaPage;
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi tìm kiếm trang Media: page = {}, size = {}", pageable.getPageNumber(),
                    pageable.getPageSize(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm kiếm trang Media: page = {}, size = {}", pageable.getPageNumber(),
                    pageable.getPageSize(), ex);
            throw MediaRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Optional<Media> findMediaById(Long id) {
        try {
            MediaEntity entity = jpaMediaRepository.findById(id)
                    .orElseThrow(() -> MediaRepositoryException.findFailed("Không tìm thấy Media với ID: " + id));
            Media media = mediaMapper.fromEntityToDomain(entity);
            return Optional.of(media);
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi tìm kiếm Media theo ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm kiếm Media theo ID: {}", id, ex);
            throw MediaRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Media updateMedia(Media media) {
        try {
            MediaEntity entity = mediaMapper.fromDomainToEntity(media);
            MediaEntity updatedEntity = jpaMediaRepository.save(entity);
            Media updatedMedia = mediaMapper.fromEntityToDomain(updatedEntity);
            log.info("Cập nhật Media thành công với ID: {}", updatedMedia.getId());
            return updatedMedia;
        } catch (DataIntegrityViolationException ex) {
            log.error("Vi phạm ràng buộc dữ liệu khi cập nhật Media: {}", media, ex);
            throw ex;
        } catch (MediaMapperException ex) {
            log.error("Lỗi mapping khi cập nhật Media: {}", media, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi cập nhật Media: {}", media, ex);
            throw MediaRepositoryException.updateFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteMediaById(Long id) {
        try {
            jpaMediaRepository.deleteById(id);
            log.info("Xóa Media thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa Media theo ID: {}", id, ex);
            throw MediaRepositoryException.deleteFailed(ex.getMessage());
        }
    }

    @Override
    public Boolean existsByUrl(String url) {
        try {
            return jpaMediaRepository.existsByUrl(url);
        } catch (Exception ex) {
            log.error("Lỗi khi kiểm tra tồn tại Media theo URL: {}", url, ex);
            throw MediaRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public List<Media> findAllByOwnerIdAndOwnerType(Specification<Media> spec) {
        log.info("Tìm kiếm Media theo OwnerId và OwnerType");
        try {
            org.springframework.data.jpa.domain.Specification<MediaEntity> jpaSpec = MediaJpaSpecificationAdapter
                    .toJpaSpecification(spec);
            return jpaMediaRepository.findAll(jpaSpec).stream()
                    .map(mediaMapper::fromEntityToDomain)
                    .toList();
        } catch (Exception ex) {
            log.error("Lỗi khi tìm kiếm Media theo OwnerId và OwnerType", ex);
            throw MediaRepositoryException.findFailed(ex.getMessage());
        }
    }
}
