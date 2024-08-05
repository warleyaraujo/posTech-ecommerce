package com.postech.gestaodeenvio.entities.orderentities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Orders {
    @Id
    private UUID id;
    private String protocol;
    private Integer serviceId;
    private Integer agencyId;
    private String contract;
    private String serviceCode;
    private BigDecimal quote;
    private BigDecimal price;
    private String coupon;
    private BigDecimal discount;
    private Integer deliveryMin;
    private Integer deliveryMax;
    private String status;
    private String reminder;
    private BigDecimal insuranceValue;
    private BigDecimal billedWeight;
    private Boolean receipt;
    private Boolean ownHand;
    private Boolean collect;
    private LocalDateTime collectScheduledAt;
    private Boolean reverse;
    private Boolean nonCommercial;
    private String authorizationCode;
    private String tracking;
    private String selfTracking;
    private String deliveryReceipt;
    private String additionalInfo;
    private String cteKey;
    private LocalDateTime paidAt;
    private LocalDateTime generatedAt;
    private LocalDateTime postedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime canceledAt;
    private LocalDateTime suspendedAt;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime parsePiAt;
    private LocalDateTime receivedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Volumes> volumes;

}
