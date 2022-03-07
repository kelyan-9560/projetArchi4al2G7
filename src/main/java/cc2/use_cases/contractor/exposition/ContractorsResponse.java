package cc2.use_cases.contractor.exposition;

import java.util.List;

public class ContractorsResponse {
    public final List<ContractorResponse> contractorResponseList;

    public ContractorsResponse(List<ContractorResponse> contractorResponseList) {
        this.contractorResponseList = contractorResponseList;
    }
}
