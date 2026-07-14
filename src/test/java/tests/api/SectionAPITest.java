package tests.api;

import api_adapters.SectionAdapter;
import api.models.sections.MoveSectionRq;
import api.models.sections.SectionRq;
import api.models.sections.SectionRs;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class SectionAPITest {

    private static final String PROJECT_CODE = "2";
    private static final String SECTION_NAME = "Section name";
    private static final String UPDATED_SECTION_NAME = "Section name_updated";
    private static final String PARENT_SECTION_NAME = "Section 1";
    private static final String CHILD_SECTION_NAME = "Section 2";
    private static final String SECTION_DESCRIPTION = "Section Description";
    private Integer createdSectionId;
    private Integer updatedSectionId;
    private Integer parentSectionId;
    private Integer childSectionId;


    @Owner("Pavel")
    @Test(priority = 1)
    public void checkCreateSection() {
        SectionRq sectionRq = SectionRq
                .builder()
                .parent_id(null)
                .name(SECTION_NAME)
                .description(SECTION_DESCRIPTION)
                .build();
        SectionRs sectionRs = SectionAdapter.createSection(sectionRq, PROJECT_CODE);
        createdSectionId = sectionRs.getId();

        assertEquals(sectionRs.getName(), SECTION_NAME, "The created section name does not match.");
    }

    @Owner("Pavel")
    @Test(priority = 2)
    public void updateSection() {
        SectionRq updatedSectionRq = SectionRq
                .builder()
                .name(UPDATED_SECTION_NAME)
                .description(SECTION_DESCRIPTION)
                .build();
        SectionRs updatedSectionRs = SectionAdapter.updateSection(createdSectionId, updatedSectionRq);
        updatedSectionId = updatedSectionRs.getId();

        assertEquals(updatedSectionRs.getName(), UPDATED_SECTION_NAME, "The section name was not updated.");
    }

    @Owner("Pavel")
    @Test(priority = 3)
    public void moveSection() {
        SectionRq parentSectionRq = SectionRq
                .builder()
                .parent_id(null)
                .name(PARENT_SECTION_NAME)
                .description(SECTION_DESCRIPTION)
                .build();
        SectionRq childSectionRq = SectionRq
                .builder()
                .parent_id(null)
                .name(CHILD_SECTION_NAME)
                .description(SECTION_DESCRIPTION)
                .build();
        SectionRs parentSectionRs = SectionAdapter.createSection(parentSectionRq, PROJECT_CODE);
        parentSectionId = parentSectionRs.getId();
        SectionRs childSectionRs = SectionAdapter.createSection(childSectionRq, PROJECT_CODE);
        childSectionId = childSectionRs.getId();
        MoveSectionRq moveSectionRq = MoveSectionRq.builder()
                .parent_id(parentSectionId)
                .after_id(null)
                .build();
        SectionRs movedSectionRs = SectionAdapter.moveSection(childSectionId, moveSectionRq);

        assertEquals(movedSectionRs.getParentId(), parentSectionId,
                "Section 2 was not added to Section 1.");
    }

    @Owner("Pavel")
    @Test(priority = 4)
    public void deleteSections() {
        SectionAdapter.deleteSectionIfCreated(childSectionId);
        SectionAdapter.deleteSectionIfCreated(parentSectionId);
        SectionAdapter.deleteSectionIfCreated(updatedSectionId);
    }
}