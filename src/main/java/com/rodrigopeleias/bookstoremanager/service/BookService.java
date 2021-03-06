package com.rodrigopeleias.bookstoremanager.service;

import com.rodrigopeleias.bookstoremanager.dto.BookDTO;
import com.rodrigopeleias.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigopeleias.bookstoremanager.entity.Book;
//import com.rodrigopeleias.bookstoremanager.mapper.BookMapper;
import com.rodrigopeleias.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class BookService {
    private BookRepository bookRepository;

    //private final BookMapper bookMapper = BookMapper.INSTANCE;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO) {
        /*Book bookToSave = Book.builder()
                .name(bookDTO.getName())
                .pages(bookDTO.getPages())
                .chapters(bookDTO.getChapters())
                .build();*/

        //Book bookToSave = BookMapper.INSTANCE.toModel(bookDTO);
        Book bookToSave = modelMapper.map(bookDTO, Book.class);

        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId())
                .build();
    }
}
