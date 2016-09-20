<tr class="cloned-table-row" style="max-width: 956px">

    <td>
        <g:hiddenField name='experiences[${i}].id' value='${experience?.id}' class="exclude-from-clone"/>

        <g:message code="job.bossName.label"/>

        <g:textField name="experiences[${i}].employer" value="${experience?.employer}"/>




        <g:message code="job.position.label"/>


        <g:textField name="experiences[${i}].occupation" value="${experience?.occupation}"/>

        <g:message code="job.periodFromTo.label"/>

        <div class="row">
            <div class="small-6 columns">
                <label style="text-align: right;">Od:(dd.mm.yyyy)
                <g:textField name="experiences[${i}].periodStart" value="${experience?.periodStart}"
                             style="text-align: right;"/>
                </label>
            </div>

            <div class="small-6 columns">
                <label>Do:(dd.mm.yyyy)
                <g:textField name="experiences[${i}].periodEnd" value="${experience?.periodEnd}"/>
                </label>
            </div>

        </div>

        <g:message code="jobOfferDescription.label"/>

        <g:textArea cols="50" rows="6" style="width: 450px" name="experiences[${i}].activities"
                    value="${experience?.activities}"/>




        <div class="del-cloned-table-row ">

            <a href="#" class="button alert tiny"><i class="fi-x"></i> <g:message code="delete" /> </a>

        </div>

    </td>

</tr>
