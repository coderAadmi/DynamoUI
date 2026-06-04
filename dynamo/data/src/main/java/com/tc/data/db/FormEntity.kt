import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tc.data.network.DomainMapper
import com.tc.domain.models.Form
import com.tc.domain.models.FormElement
import kotlinx.serialization.Serializable

@Entity(tableName = "forms")
data class FormEntity(
    @PrimaryKey(autoGenerate = false)
    val formId: String,
    val title: String,
    val elements: List<FormElementEntity>
) : DomainMapper {
    override fun  toDomain()
            = Form(formId, title, elements.map { it.toDomain() } )
}

@Serializable
data class FormElementEntity(
    val id: String,
    val title: String,
    val type: String,
    val imgUrl: String? = null,
    val options: List<String>? = null
) : DomainMapper {
    override fun toDomain() = FormElement(
        id =  id,
        title  = title,
        type = when (type) {
            "text" -> FormElement.Type.TEXT
            "input" -> FormElement.Type.INPUT
            "image" -> FormElement.Type.IMG
            "radioGroup" -> FormElement.Type.RADIOS
            "checkBox" -> FormElement.Type.CHECKBOX
            else -> FormElement.Type.TEXT
        },
        imgUrl = imgUrl,
        options = options
    )
}