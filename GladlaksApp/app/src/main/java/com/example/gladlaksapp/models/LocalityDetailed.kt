package com.example.gladlaksapp.models

// ------------- Class Structure ------------- //
// LocalityDetailed {
//      LocalityWeek {
//              BathTreatments [
//                  Treatment {
//                      TreatmentDetail {
//                          Substance {
//                          }
//                      }
//                  }
//              ]
//              (Duplicate for infeed-treatments)
//              MechanicalRemoval
//      }
// }


// ------------- Outer Wrapper Class ------------- //

data class LocalityDetailed (
    val localityName: String,
    val localityWeek: LocalityWeek,
    val liceCountPreviousWeek: LiceCountPreviousWeek,

)

// ------------- Detailed Info ------------- //

data class LocalityWeek (
    val id: Int,
    val localityNo: Int,
    val year: Int,
    val week: Int,
    val hasReportedLice: Boolean,
    val hasMechanicalRemoval: Boolean,
    val hasBathTreatment: Boolean,
    val hasInFeedTreatment: Boolean,
    val hasCleanerFishDeployed: Boolean,
    val avgAdultFemaleLice: Number,
    val avgMobileLice: Number,
    val avgStationaryLice: Number,
    val bathTreatments: List<Treatment>?,
    val inFeedTreatments: List<Treatment>?,
    val mechanicalRemoval: MechanicalRemoval?,
)


// ------------- Previous Week Lice Count ------------- //

data class LiceCountPreviousWeek (
    val year: Int,
    val week: Int,
    val hasReportedLice: Boolean,
    val avgAdultFemaleLice: Number,
    val avgMobileLice: Number,
    val avgStationaryLice: Number

)


// ------------- Mechanical Removal ------------- //

data class MechanicalRemoval(
    val id: Int,
    val entireLocality: Boolean,
    val method: String,
)


// ------------- In-feed Or Bath Treatments ------------- //

data class Treatment(
    val id: Int,
    val treatmentType: String,
    val startDate: String,
    val endDate: String,
    val entireLocality: Boolean,
    val treatmentDetail: List<TreatmentDetail>
)

data class TreatmentDetail(
    val id: Int,
    val quantity: Int,
    val concentration: Number,
    val substanceId: Int,
    val substance: Substance,
)

data class Substance (
    val id: Int,
    val name: String
)


// ------------- Cleaner Fish ------------- //
