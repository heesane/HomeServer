package hhs.server.domain.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "road_map")
public class RoadMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rm_id")
    private Long id;

    @Column(name = "rm_name")
    private String name;

    @Column(name = "rm_desc")
    private String description;

    @Column(name = "rm_img")
    private String imageUrl;

}
