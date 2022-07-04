package com.example.projecttrain.cad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CadEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String documentPath;

    @Column(columnDefinition = "nvarchar(max)")
    private String bookMarks;

    @Column(columnDefinition = "nvarchar(max)")
    private String bookmarksValue;

}
