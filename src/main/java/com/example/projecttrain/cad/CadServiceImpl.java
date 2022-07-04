package com.example.projecttrain.cad;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CadServiceImpl implements CadService{

    private final CadRepository cadRepository;
    private final String uploadFolderPath = "D:\\ProjectTrain\\src\\main\\resources\\static";


    @Override
    public CadEntity getBookMarks( MultipartFile multipartFile) throws Exception {
        CadEntity cadEntity = new CadEntity();
        ObjectMapper  objectMapper = new ObjectMapper();
        try {
            List<BookMarkDto> bookMarkDtos = new ArrayList<>();
            byte[] data = multipartFile.getBytes();
            Path path = Paths.get(uploadFolderPath + multipartFile.getOriginalFilename());
            Files.write(path, data);
            XWPFDocument document = new XWPFDocument(multipartFile.getInputStream());
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            List<XWPFTable> tables = document.getTables();
            tables.forEach(xwpfTable ->  {
                List<XWPFTableRow> rows = xwpfTable.getRows();
                rows.forEach(xwpfTableRow -> {
                    List<XWPFTableCell> cells = xwpfTableRow.getTableCells();
                    cells.forEach(xwpfTableCell -> {
                        List<XWPFParagraph> paragraphs1 = xwpfTableCell.getParagraphs();
                        paragraphs1.forEach(xwpfParagraph -> {
                            CTP ctp = xwpfParagraph.getCTP();
                            String[] bookmark;
                            List<CTBookmark> bookmarks = ctp.getBookmarkStartList();
                            bookmarks.forEach(ctBookmark -> {
                                System.out.println(ctBookmark.getName());
                                String ctBookmarks = ctBookmark.getName();
                                BookMarkDto bookMarkDto = new BookMarkDto(ctBookmarks, "");
                                bookMarkDtos.add(bookMarkDto);
                            });
                        });
                    });
                });
            });
            for (XWPFParagraph paragraph : paragraphs) {
                CTP ctp = paragraph.getCTP();
                List<CTBookmark> bookmarks = ctp.getBookmarkStartList();
                for (CTBookmark bookmark : bookmarks) {
                    String paraText = paragraph.getText();
                    System.out.println( bookmark.getName());
                    String ctBookmarks = bookmark.getName();
                    BookMarkDto bookMarkDto = new BookMarkDto(ctBookmarks, "");
                    bookMarkDtos.add(bookMarkDto);
                }
            }
            cadEntity.setBookMarks(objectMapper.writeValueAsString(bookMarkDtos));
            cadEntity.setFileName(multipartFile.getOriginalFilename());
            cadEntity.setDocumentPath(path.toString());
            cadRepository.save(cadEntity);
        }catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void uploadToLocal(MultipartFile multipartFile) {
        try {
            CadEntity cadEntity = new CadEntity();
            List<String> bookmarkList = new ArrayList<>();
            byte[] data = multipartFile.getBytes();
            Path path = Paths.get(uploadFolderPath + multipartFile.getOriginalFilename());
            Files.write(path, data);
            XWPFDocument document = new XWPFDocument(multipartFile.getInputStream());
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            List<XWPFTable> tables = document.getTables();
            tables.forEach(xwpfTable ->  {
                List<XWPFTableRow> rows = xwpfTable.getRows();
                    rows.forEach(xwpfTableRow -> {
                        List<XWPFTableCell> cells = xwpfTableRow.getTableCells();
                        cells.forEach(xwpfTableCell -> {
                            List<XWPFParagraph> paragraphs1 = xwpfTableCell.getParagraphs();
                            for (XWPFParagraph paragraph : paragraphs1) {
                                CTP ctp = paragraph.getCTP();
                                List<CTBookmark> bookmarks = ctp.getBookmarkStartList();
                                for (CTBookmark bookmark : bookmarks) {
                                    String paraText = paragraph.getText();
                                    System.out.println(bookmark.getName());
                                    String ctBookmarks = bookmark.getName();
                                    bookmarkList.add(ctBookmarks);
                                }
                            }
                        });
                    });


            });
            for (XWPFParagraph paragraph : paragraphs) {
                CTP ctp = paragraph.getCTP();
                List<CTBookmark> bookmarks = ctp.getBookmarkStartList();
                for (CTBookmark bookmark : bookmarks) {
                    String paraText = paragraph.getText();
                    System.out.println( bookmark.getName());
                    String ctBookmarks = bookmark.getName();
                    bookmarkList.add(ctBookmarks);
                }
            }
            cadEntity.setBookMarks(bookmarkList.toString());
            String multipartFile1 = multipartFile.getName();
        }catch (IOException e) {
            e.printStackTrace();

        }
    }



}
